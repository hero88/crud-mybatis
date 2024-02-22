package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.EmployeeRequest;
import com.allxone.mybatisprojectbackend.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse updateEmployee(EmployeeRequest employeeRequest);
    void deleteEmployeeById(Long id);
    EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse getEmployeeById(Long id);
}
