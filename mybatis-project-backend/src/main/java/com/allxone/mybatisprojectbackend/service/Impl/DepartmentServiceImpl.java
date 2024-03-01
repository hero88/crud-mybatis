package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.DepartmentConvert;
import com.allxone.mybatisprojectbackend.dto.request.DepartmentRequest;
import com.allxone.mybatisprojectbackend.dto.response.DepartmentResponse;
import com.allxone.mybatisprojectbackend.mapper.PayrollMapper;
import com.allxone.mybatisprojectbackend.mapper.TaxInformationMapper;
import com.allxone.mybatisprojectbackend.mapper.DepartmentMapper;
import com.allxone.mybatisprojectbackend.model.Payroll;
import com.allxone.mybatisprojectbackend.model.TaxInformation;
import com.allxone.mybatisprojectbackend.model.Department;
import com.allxone.mybatisprojectbackend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentMapper.getAllDepartments()
                .stream()
                .map(DepartmentConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse updateDepartment(DepartmentRequest DepartmentRequest) {
        Department Department = DepartmentConvert.toDepartment(DepartmentRequest);
        departmentMapper.updateDepartment(Department);
        return getDepartmentById(Department.getId());
    }

    @Override
    public void deleteDepartmentById(Integer id) {
        departmentMapper.deleteDepartmentById(id);
    }

    @Override
    public DepartmentResponse saveDepartment(DepartmentRequest DepartmentRequest) {
        Department Department = DepartmentConvert.toDepartment(DepartmentRequest);
        departmentMapper.saveDepartment(Department);
        return getDepartmentById(Department.getId());
    }

    @Override
    public DepartmentResponse getDepartmentById(Integer id) {
        Department Department = departmentMapper.getDepartmentById(id);
        return DepartmentConvert.toDto(Department);
    }
}

