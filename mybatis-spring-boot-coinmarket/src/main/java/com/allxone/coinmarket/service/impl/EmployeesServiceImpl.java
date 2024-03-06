package com.allxone.coinmarket.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.allxone.coinmarket.dto.response.EmployeeDTO;
import com.allxone.coinmarket.dto.response.PageResult;

import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.dto.EmployeesDto;
import com.allxone.coinmarket.exception.auth.AuthenticateException;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.mapper.DepartmentsMapper;
import com.allxone.coinmarket.mapper.EmployeesMapper;
import com.allxone.coinmarket.mapper.TaxInformationMapper;
import com.allxone.coinmarket.model.Coins;
import com.allxone.coinmarket.model.CoinsExample;
import com.allxone.coinmarket.model.Departments;
import com.allxone.coinmarket.model.DepartmentsExample;
import com.allxone.coinmarket.model.Employees;
import com.allxone.coinmarket.model.EmployeesExample;
import com.allxone.coinmarket.model.TaxInformation;
import com.allxone.coinmarket.model.TaxInformationExample;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.EmployeesService;
import com.allxone.coinmarket.service.UserService;
import com.allxone.coinmarket.utilities.ValidatorUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	EmployeesMapper employeesMapper;

	@Autowired
	TaxInformationMapper taxInformationMapper;

	@Autowired
	UserService userService;

	@Autowired
	DepartmentsMapper departmentsMapper;

	@Override
	public void addToEmployee(EmployeesDto employeesDto) throws ParamInvalidException {

		ValidatorUtils.checkNullParam(employeesDto.getBirthday(), employeesDto.getContactNumber(),
				employeesDto.getFirstName(), employeesDto.getLastName(), employeesDto.getEmail(),
				employeesDto.getDepartmentId(), employeesDto.getPosition());

		ValidatorUtils.checkEmail(employeesDto.getEmail());
		ValidatorUtils.checkPhone(employeesDto.getContactNumber());

		Employees employees = new Employees();
		BeanUtils.copyProperties(employeesDto, employees);

		Users users = userService.getLoggedUser();
		employees.setUserId(users.getId());
		employees.setCreatedAt(new Date());
		employees.setHireDate(new Date());

		Gson gson = new Gson();
		String json = gson.toJson(employeesDto.getInsuranceIds());

		employees.setInsuranceIds(json);

		int affectedRows = employeesMapper.insert(employees);

		if (affectedRows == 1) {
			
			LocalDate firstDayOfNextMonth = LocalDate.of(LocalDate.now().getYear(),
					LocalDate.now().getMonthValue() + 1, 1);
			Date date = Date.from(firstDayOfNextMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			TaxInformation taxInformation = TaxInformation.builder().createdAt(new Date()).employeeId(employees.getId())
					.taxRate(employeesDto.getTaxRate())
					.taxExemption(employeesDto.getTaxRate().compareTo(BigDecimal.ZERO) == 0)
					.status(false)
					.dateStart(date)
					.build();
			

			taxInformationMapper.insert(taxInformation);
		}

	}

	@Override
	public void updateEmployee(EmployeesDto employeesDto) throws ParamInvalidException {

		ValidatorUtils.checkNullParam(employeesDto.getId(), employeesDto.getBirthday(), employeesDto.getContactNumber(),
				employeesDto.getFirstName(), employeesDto.getLastName(), employeesDto.getEmail(),
				employeesDto.getDepartmentId(), employeesDto.getPosition());

		ValidatorUtils.checkEmail(employeesDto.getEmail());
		ValidatorUtils.checkPhone(employeesDto.getContactNumber());

		Employees dbEmployees = employeesMapper.selectByPrimaryKey(employeesDto.getId());

		Date createAt = dbEmployees.getCreatedAt();
		Date hireDate = dbEmployees.getHireDate();

		BeanUtils.copyProperties(employeesDto, dbEmployees);
		dbEmployees.setCreatedAt(createAt);
		dbEmployees.setHireDate(hireDate);
		dbEmployees.setUpdatedAt(new Date());

		Gson gson = new Gson();
		String json = gson.toJson(employeesDto.getInsuranceIds());
		dbEmployees.setInsuranceIds(json);

		Users users = userService.getLoggedUser();
		dbEmployees.setUserId(users.getId());

		employeesMapper.updateByPrimaryKey(dbEmployees);

		TaxInformationExample taxInformationExample = new TaxInformationExample();
		taxInformationExample.createCriteria().andEmployeeIdEqualTo(dbEmployees.getId());

		List<TaxInformation> listTaxInformations = taxInformationMapper.selectByExample(taxInformationExample);

		LocalDate firstDayOfNextMonth = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 1, 1);
		Date date = Date.from(firstDayOfNextMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

		if (listTaxInformations.size() == 0) {


			TaxInformation taxInformation = TaxInformation.builder().createdAt(new Date())
					.employeeId(dbEmployees.getId()).taxRate(employeesDto.getTaxRate())
					.taxExemption(employeesDto.getTaxRate().compareTo(BigDecimal.ZERO) == 0).status(false)
					.dateStart(date).build();

			taxInformationMapper.insert(taxInformation);
		}else if (listTaxInformations != null) {

			if (listTaxInformations.size() == 1) {

				TaxInformation taxInformation = TaxInformation.builder().createdAt(new Date())
						.employeeId(dbEmployees.getId()).taxRate(employeesDto.getTaxRate())
						.taxExemption(employeesDto.getTaxRate().compareTo(BigDecimal.ZERO) == 0).status(false)
						.dateStart(date).build();

				taxInformationMapper.insert(taxInformation);

			} else if (listTaxInformations.size() == 2) {

				TaxInformation taxInformation = listTaxInformations.stream().filter(item -> !item.getStatus())
						.findFirst().orElse(null);

				if (taxInformation != null) {
					taxInformation.setUpdatedAt(new Date());
					taxInformation.setTaxRate(employeesDto.getTaxRate());
					taxInformation.setTaxExemption(employeesDto.getTaxRate().compareTo(BigDecimal.ZERO) == 0);
					taxInformation.setDateStart(date);

					taxInformationMapper.updateByPrimaryKey(taxInformation);
				}
			}

		}

	}

	@Override
	public void deleteEmployee(Long id) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(id);
		employeesMapper.deleteByPrimaryKey(id);

		TaxInformationExample taxInformationExample = new TaxInformationExample();
		taxInformationExample.createCriteria().andEmployeeIdEqualTo(id);

		List<TaxInformation> taxInformationList = taxInformationMapper.selectByExample(taxInformationExample);

		if (!taxInformationList.isEmpty()) {
			TaxInformation taxInformation = taxInformationList.get(0);
			taxInformationMapper.deleteByPrimaryKey(taxInformation.getId());
		}

	}

	@Override
	public EmployeesDto findById(Long id) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(id);

		List<TaxInformation> listTaxInformations = taxInformationMapper.selectTaxInformation(id);

		if (listTaxInformations != null) {

			if (listTaxInformations.size() == 2) {

				TaxInformation firstTaxInformation = listTaxInformations.get(0);
				TaxInformation secondTaxInformation = listTaxInformations.get(1);
				
				if(firstTaxInformation!=null) {
					firstTaxInformation.setStatus(true);
					
					taxInformationMapper.updateByPrimaryKey(firstTaxInformation);
				}
				
				if(secondTaxInformation!=null) {
					secondTaxInformation.setStatus(false);
					taxInformationMapper.updateByPrimaryKey(secondTaxInformation);
				}

			}

		}

		return convertToEmployeesDto(employeesMapper.selectByPrimaryKey(id));
	}

	@Override
	public PageResult<EmployeesDto> findAll(int page, int size) {

		List<EmployeesDto> employeesDtoList = employeesMapper.getEmployees(page, size).stream()
				.map(item -> convertToEmployeesDto(item)).toList();

		EmployeesExample employeesExample = new EmployeesExample();

		int totalEmployees = employeesMapper.selectByExample(employeesExample).size();
		int totalPages = (int) Math.ceil((double) totalEmployees / size);

		return new PageResult(employeesDtoList, totalPages);

	}

	public EmployeesDto convertToEmployeesDto(Employees employees) {

		EmployeesDto employeesDto = new EmployeesDto();
		BeanUtils.copyProperties(employees, employeesDto);

		List<Integer> list = convertJsonToList(employees.getInsuranceIds());
		employeesDto.setInsuranceIds(list);
		

		if (employees.getId() != null) {

			TaxInformationExample taxInformationExample = new TaxInformationExample();
			taxInformationExample.createCriteria().andEmployeeIdEqualTo(employees.getId());

			List<TaxInformation> taxInformationList = taxInformationMapper.findByEmployees(employees.getId());

			if (!taxInformationList.isEmpty()) {
				TaxInformation taxInformation = taxInformationList.get(0);
				employeesDto.setTaxRate(taxInformation.getTaxRate());
				employeesDto.setTaxInformationId(taxInformation.getId());
			}else {
				TaxInformation taxInformation = taxInformationMapper
						.selectByExample(taxInformationExample).stream().findFirst().orElse(null);
				
				if(taxInformation!=null) {
					employeesDto.setTaxRate(taxInformation.getTaxRate());
					employeesDto.setTaxInformationId(taxInformation.getId());
				}
			}

		}

		return employeesDto;
	}

	@Override
	public List<Departments> getAllDepartments() {
		DepartmentsExample departmentsExample = new DepartmentsExample();
		return departmentsMapper.selectByExample(departmentsExample);
	}

	@Override
	public List<EmployeeDTO> findAllEmployeeNotTermination() {
		return employeesMapper.findAllEmployeeNotTermination();
	}

	@Override
	public List<Employees> findAllEmployee() {
		return employeesMapper.selectByExample(new EmployeesExample());
	}

	private List<Integer> convertJsonToList(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, new TypeToken<List<Integer>>() {
		}.getType());
	}

}
