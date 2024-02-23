package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.PayrollConvert;
import com.allxone.mybatisprojectbackend.dto.request.PayrollRequest;
import com.allxone.mybatisprojectbackend.dto.response.PayrollResponse;
import com.allxone.mybatisprojectbackend.mapper.PayrollMapper;
import com.allxone.mybatisprojectbackend.model.Payroll;
import com.allxone.mybatisprojectbackend.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {
    private final PayrollMapper payrollMapper;
    @Override
    public List<PayrollResponse> getAllPayrolls() {
        return payrollMapper.getAllPayrolls()
                .stream()
                .map(PayrollConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PayrollResponse savePayroll(PayrollRequest payrollRequest) {
        Payroll Payroll = PayrollConvert.toPayroll(payrollRequest);
        payrollMapper.savePayroll(Payroll);
        return getPayrollById(Payroll.getId());
    }

    @Override
    public PayrollResponse getPayrollById(Long id) {
        Payroll Payroll = payrollMapper.getPayrollById(id);
        return PayrollConvert.toDto(Payroll);
    }
}
