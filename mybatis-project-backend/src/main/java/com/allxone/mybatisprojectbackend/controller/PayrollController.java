package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.PayrollRequest;
import com.allxone.mybatisprojectbackend.dto.request.PayrollRequest;
import com.allxone.mybatisprojectbackend.dto.response.PayrollResponse;
import com.allxone.mybatisprojectbackend.dto.response.PayrollResponse;
import com.allxone.mybatisprojectbackend.service.PayrollService;
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
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping("/getAllPayrolls")
    public CommonResponse<List<PayrollResponse>> getAllPayroll() {
        try {
            List<PayrollResponse> data = payrollService.getAllPayrolls();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/savePayroll")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<PayrollResponse> savePayroll(@RequestBody PayrollRequest payrollRequest) {

        try {
            PayrollResponse data = payrollService.savePayroll(payrollRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updatePayroll")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<PayrollResponse> updatePayroll(@RequestBody PayrollRequest PayrollRequest) {

        try {
            PayrollResponse data = payrollService.updatePayroll(PayrollRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }
    }

}
