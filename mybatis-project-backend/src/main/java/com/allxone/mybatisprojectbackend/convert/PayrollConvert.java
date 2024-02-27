package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.PayrollRequest;
import com.allxone.mybatisprojectbackend.dto.response.PayrollResponse;
import com.allxone.mybatisprojectbackend.model.Payroll;
import org.springframework.stereotype.Component;

@Component
public class PayrollConvert {
    public static PayrollResponse toDto(Payroll payroll) {
        return PayrollResponse.builder()
                .id(payroll.getId())
                .employeeId(payroll.getEmployeeId())
                .salary(payroll.getSalary())
                .bonus(payroll.getBonus())
                .deductions(payroll.getDeductions())
                .netSalary(payroll.getNetSalary())
                .periodStart(payroll.getPeriodStart())
                .periodEnd(payroll.getPeriodEnd())
                .createdAt(payroll.getCreatedAt())
                .updatedAt(payroll.getUpdatedAt())
                .build();
    }

    public static Payroll toPayroll(PayrollRequest payrollRequest) {
        return Payroll.builder()
                .employeeId(payrollRequest.getEmployeeId())
                .salary(payrollRequest.getSalary())
                .bonus(payrollRequest.getBonus())
                .deductions(payrollRequest.getDeductions())
                .netSalary(payrollRequest.getNetSalary())
                .periodStart(payrollRequest.getPeriodStart())
                .periodEnd(payrollRequest.getPeriodEnd())
                .build();
    }
}
