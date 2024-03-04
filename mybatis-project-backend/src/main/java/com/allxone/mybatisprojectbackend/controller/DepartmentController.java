package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.DepartmentRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinResponse;
import com.allxone.mybatisprojectbackend.dto.response.DepartmentResponse;
import com.allxone.mybatisprojectbackend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Department")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/getAllDepartment")
    public CommonResponse<List<DepartmentResponse>> getAllDepartments() {
        try {
            List<DepartmentResponse> data = departmentService.getAllDepartments();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/saveDepartment")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<DepartmentResponse> saveDepartment(@RequestBody DepartmentRequest departmentRequest) {

        try {
            DepartmentResponse data = departmentService.saveDepartment(departmentRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @GetMapping("/getDepartmentById/{id}")
    public CommonResponse<DepartmentResponse> getDepartmentById(@PathVariable Integer id) {
        try {
            DepartmentResponse data = departmentService.getDepartmentById(id);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updateDepartment")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<DepartmentResponse> updateDepartment(@RequestBody DepartmentRequest departmentRequest) {

        try {
            DepartmentResponse data = departmentService.updateDepartment(departmentRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }
    }
}
