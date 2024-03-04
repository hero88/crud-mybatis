package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.HolidayConvert;
import com.allxone.mybatisprojectbackend.convert.PayrollConvert;
import com.allxone.mybatisprojectbackend.dto.request.PayrollRequest;
import com.allxone.mybatisprojectbackend.dto.response.PayrollResponse;
import com.allxone.mybatisprojectbackend.mapper.HolidayMapper;
import com.allxone.mybatisprojectbackend.mapper.InsuranceTypeMapper;
import com.allxone.mybatisprojectbackend.mapper.PayrollMapper;
import com.allxone.mybatisprojectbackend.mapper.TaxInformationMapper;
import com.allxone.mybatisprojectbackend.model.Holiday;
import com.allxone.mybatisprojectbackend.model.InsuranceType;
import com.allxone.mybatisprojectbackend.model.Payroll;
import com.allxone.mybatisprojectbackend.model.TaxInformation;
import com.allxone.mybatisprojectbackend.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final PayrollMapper payrollMapper;
    private final InsuranceTypeMapper insuranceTypeMapper;
    private final TaxInformationMapper taxInformationMapper;
    private final HolidayMapper holidayMapper;

    @Override
    public List<PayrollResponse> getAllPayrolls() {
        return payrollMapper.getAllPayrolls()
                .stream()
                .map(PayrollConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PayrollResponse savePayroll(PayrollRequest payrollRequest) {
        Payroll payroll = PayrollConvert.toPayroll(payrollRequest);
        payrollMapper.savePayroll(payroll);
        return getPayrollById(payroll.getId());
    }

    @Override
    public PayrollResponse getPayrollById(Long id) {
        Payroll payroll = payrollMapper.getPayrollById(id);
        return PayrollConvert.toDto(payroll);
    }

    @Override
    public PayrollResponse updatePayroll(PayrollRequest payrollRequest) {
        Payroll payroll = PayrollConvert.toPayroll(payrollRequest);

        Double insuranceRate =
                insuranceTypeMapper.getInsuranceTypeByEmployeeId(payroll.getEmployeeId())
                        .stream()
                        .mapToDouble(InsuranceType::getInsuranceRate)
                        .reduce(0.0, Double::sum);

        TaxInformation taxInformation =
                taxInformationMapper.getTaxInformationByEmployeeId(payroll.getEmployeeId());

        Double holidayDays = holidayMapper.getTotalHolidayByPayrollId(payroll.getId())
                .stream()
                .mapToDouble(Holiday::getDurationDays)
                .reduce(0.0, Double::sum);

        payroll.setNetSalary(getNetSalary(payroll,
                holidayDays * 8,
                1.0,
                taxInformation.getTaxRate(),
                insuranceRate));

        payrollMapper.updatePayroll(payroll);
        return getPayrollById(payroll.getId());
    }

    public Double getNetSalary(Payroll payroll,
                               Double hour,
                               Double bonus,
                               Double taxRate,
                               Double insuranceRate) {
        return payroll.getNetSalary()
                + (payroll.getSalary() * hour * (100 - taxRate - insuranceRate)) / 100
                + bonus
                - payroll.getDeductions();
    }
}
