package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.EmployeeConvert;
import com.allxone.mybatisprojectbackend.dto.request.EmployeeRequest;
import com.allxone.mybatisprojectbackend.dto.response.EmployeeResponse;
import com.allxone.mybatisprojectbackend.mapper.EmployeeMapper;
import com.allxone.mybatisprojectbackend.model.Employee;
import com.allxone.mybatisprojectbackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.getAllEmployees()
        .stream()
        .map(EmployeeConvert::toDto)
        .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest) {
        Employee Employee = EmployeeConvert.toEmployee(employeeRequest);
        employeeMapper.updateEmployee(Employee);
        return getEmployeeById(Employee.getId());
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeMapper.deleteEmployeeById(id);
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee Employee = EmployeeConvert.toEmployee(employeeRequest);
        employeeMapper.saveEmployee(Employee);
        return getEmployeeById(Employee.getId());
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee Employee = employeeMapper.getEmployeeById(id);
        return EmployeeConvert.toDto(Employee);
    }

}
