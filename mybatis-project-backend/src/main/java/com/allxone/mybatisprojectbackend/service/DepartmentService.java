package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.DepartmentRequest;
import com.allxone.mybatisprojectbackend.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse updateDepartment(DepartmentRequest departmentRequest);
    void deleteDepartmentById(Integer id);
    DepartmentResponse saveDepartment(DepartmentRequest departmentRequest);
    DepartmentResponse getDepartmentById(Integer id);
}
