package com.allxone.coinmarket.service;


import java.util.List;

import com.allxone.coinmarket.dto.EmployeesDto;
import com.allxone.coinmarket.dto.response.EmployeeDTO;
import com.allxone.coinmarket.dto.response.PageResult;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.Departments;
import com.allxone.coinmarket.model.Employees;

public interface EmployeesService {

	PageResult<EmployeesDto> findAll(int page, int size);

	EmployeesDto findById(Long id) throws ParamInvalidException;

	void deleteEmployee(Long id) throws ParamInvalidException;

	void updateEmployee(EmployeesDto employeesDto) throws ParamInvalidException;

	void addToEmployee(EmployeesDto employeesDto) throws ParamInvalidException;

	List<Departments> getAllDepartments();

	List<EmployeeDTO> findAllEmployeeNotTermination();

	List<Employees> findAllEmployee();
}
