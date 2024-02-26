package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.DepartmentRequest;
import com.allxone.mybatisprojectbackend.dto.response.DepartmentResponse;
import com.allxone.mybatisprojectbackend.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConvert {

    public static DepartmentResponse toDto(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public static Department toDepartment(DepartmentRequest departmentRequest) {
        return Department.builder()
                .id(departmentRequest.getId())
                .name(departmentRequest.getName())
                .build();
    }
}
