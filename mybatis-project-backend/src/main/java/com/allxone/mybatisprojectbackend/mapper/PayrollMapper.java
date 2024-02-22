package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.Payroll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayrollMapper {
    List<Payroll> getAllPayrolls();
    void updatePayroll(Payroll payroll);
    void deletePayrollById(Long id);
    void savePayroll(Payroll payroll);
    Payroll getPayrollById(Long id);
}
