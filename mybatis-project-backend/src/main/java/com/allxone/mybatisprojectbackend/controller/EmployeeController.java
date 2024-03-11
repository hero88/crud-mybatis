package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.EmployeeRequest;
import com.allxone.mybatisprojectbackend.dto.response.EmployeeResponse;
import com.allxone.mybatisprojectbackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public CommonResponse<List<EmployeeResponse>> getAllEmployees() {
        try {
            List<EmployeeResponse> data = employeeService.getAllEmployees();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/saveEmployee")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {

        try {
            EmployeeResponse data = employeeService.saveEmployee(employeeRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
//    @PreAuthorize("hasAuthority('admin:delete')")
    public CommonResponse<Long> deleteEmployeeById(@PathVariable Long id) {

//        try {
            employeeService.deleteEmployeeById(id);
            return CommonResponse.success(id);
//        } catch (Exception e) {
//            return CommonResponse.error(null);
//        }
    }

    @PutMapping("/updateEmployee")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequest employeeRequest) {

        try {
            EmployeeResponse data = employeeService.updateEmployee(employeeRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }

    }
}
