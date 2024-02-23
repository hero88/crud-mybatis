package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.PayrollRequest;
import com.allxone.mybatisprojectbackend.dto.response.PayrollResponse;

import java.util.List;

public interface PayrollService {
    List<PayrollResponse> getAllPayrolls();
    PayrollResponse savePayroll(PayrollRequest payrollRequest);
    PayrollResponse getPayrollById(Long id);
}
