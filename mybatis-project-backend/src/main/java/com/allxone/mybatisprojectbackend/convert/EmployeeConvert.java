package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.EmployeeRequest;
import com.allxone.mybatisprojectbackend.dto.response.EmployeeResponse;
import com.allxone.mybatisprojectbackend.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConvert {

    public static EmployeeResponse toDto(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .userId(employee.getUserId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthday(employee.getBirthday())
                .gender(employee.getGender())
                .contactNumber(employee.getContactNumber())
                .email(employee.getEmail())
                .departmentId(employee.getDepartmentId())
                .position(employee.getPosition())
                .hireDate(employee.getHireDate())
                .terminationDate(employee.getTerminationDate())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    public static Employee toEmployee(EmployeeRequest employeeRequest) {
        return Employee.builder()
                .id(employeeRequest.getId())
                .userId(employeeRequest.getUserId())
                .firstname(employeeRequest.getFirstname())
                .lastname(employeeRequest.getLastname())
                .birthday(employeeRequest.getBirthday())
                .gender(employeeRequest.getGender())
                .contactNumber(employeeRequest.getContactNumber())
                .email(employeeRequest.getEmail())
                .departmentId(employeeRequest.getDepartmentId())
                .position(employeeRequest.getPosition())
                .hireDate(employeeRequest.getHireDate())
                .terminationDate(employeeRequest.getTerminationDate())
                .build();
    }
}
