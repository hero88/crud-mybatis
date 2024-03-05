package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.PayrollConvert;
import com.allxone.mybatisprojectbackend.dto.request.PayrollRequest;
import com.allxone.mybatisprojectbackend.dto.response.PayrollResponse;
import com.allxone.mybatisprojectbackend.mapper.HolidayMapper;
import com.allxone.mybatisprojectbackend.mapper.InsuranceTypeMapper;
import com.allxone.mybatisprojectbackend.mapper.PayrollMapper;
import com.allxone.mybatisprojectbackend.mapper.TaxInformationMapper;
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

        Payroll payroll = payrollMapper.getPayrollById(payrollRequest.getId());

        Double insuranceRate =
                insuranceTypeMapper.getInsuranceTypeByEmployeeId(payroll.getEmployeeId())
                        .stream()
                        .mapToDouble(insuranceType ->
                                insuranceType.getInsuranceRate() != null
                                        ?
                                        insuranceType.getInsuranceRate()
                                        : 0.0
                        )
                        .reduce(0.0, Double::sum);

        TaxInformation taxInformation =
                taxInformationMapper.getTaxInformationByEmployeeId(payroll.getEmployeeId());
//      tien nghi le cu
        double oldHolidayDays = getTotalHolidayByPayroll(payroll);
        Double oldLeavePaidDays = Double.valueOf(payroll.getLeavePaidDays());

        Double oldNetSalaryHolidayAndLeavePaidDays =
                getNetSalaryHolidayAndLeavePaidDays(
                        payroll,
                        oldHolidayDays * 8 + oldLeavePaidDays * 8,
                        taxInformation.getTaxRate(),
                        insuranceRate);
        payroll.setNetSalary(payroll.getNetSalary() - oldNetSalaryHolidayAndLeavePaidDays);

        if (payrollRequest.getSalary() != null) {
            payroll.setSalary(payrollRequest.getSalary());
        }

        if (payrollRequest.getBonus() != null) {
            payroll.setBonus(payrollRequest.getBonus());
        }

        if (payrollRequest.getDeductions() != null) {
            payroll.setDeductions(payrollRequest.getDeductions());
        }

        if (payrollRequest.getLeavePaidDays() != null) {
            payroll.setLeavePaidDays(payrollRequest.getLeavePaidDays());
        }

        if (payrollRequest.getHolidayIds() != null) {
            payroll.setHolidayIds(payrollRequest.getHolidayIds());
        }

        payrollMapper.updatePayroll(payroll);

        if (payroll.getHolidayIds() != null && payroll.getLeavePaidDays() != null) {
            Double holidayDays = getTotalHolidayByPayroll(payroll);

            Double leavePaidDays = Double.valueOf(payroll.getLeavePaidDays());
            payroll.setNetSalary(getNetSalary(payroll,
                    holidayDays * 8 + leavePaidDays * 8,
                    1.0,
                    taxInformation.getTaxRate(),
                    insuranceRate));
        }
        payrollMapper.updatePayroll(payroll);
        return getPayrollById(payroll.getId());
    }

    public Double getNetSalaryHolidayAndLeavePaidDays(Payroll payroll,
                                                      Double hour,
                                                      Double taxRate,
                                                      Double insuranceRate) {
        return payroll.getNetSalary() - (payroll.getSalary() * hour * (100 - taxRate - insuranceRate)) / 100;
    }

    public Double getNetSalary(Payroll payroll,
                               Double hour,
                               Double bonus,
                               Double taxRate,
                               Double insuranceRate) {
        double deductions = payroll.getDeductions() != null ? payroll.getDeductions() : 0.0;
        return payroll.getNetSalary()
                + (payroll.getSalary() * hour * (100 - taxRate - insuranceRate)) / 100
                + bonus
                - deductions;
    }

    public Double getTotalHolidayByPayroll(Payroll payroll) {
        return holidayMapper.getTotalHolidayByPayrollId(payroll.getId())
                .stream()
                .mapToDouble(holiday ->
                        holiday.getDurationDays() != null
                                ?
                                holiday.getDurationDays()
                                : 0.0
                )
                .reduce(0.0, Double::sum);
    }
}
