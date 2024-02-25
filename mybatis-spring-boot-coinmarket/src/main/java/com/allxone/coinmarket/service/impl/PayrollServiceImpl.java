package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.dto.response.PayrollDTO;
import com.allxone.coinmarket.mapper.PayrollMapper;
import com.allxone.coinmarket.mapper.TaxInformationMapper;
import com.allxone.coinmarket.mapper.TimeTrackingMapper;
import com.allxone.coinmarket.model.Payroll;
import com.allxone.coinmarket.model.PayrollExample;
import com.allxone.coinmarket.model.TaxInformationExample;
import com.allxone.coinmarket.model.TimeTracking;
import com.allxone.coinmarket.service.PayrollService;
import com.allxone.coinmarket.service.TimeTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollMapper payrollMapper;

    @Autowired
    private TimeTrackingService timeTrackingService;

    @Autowired
    private TaxInformationMapper taxInformationMapper;

    @Override
    public List<PayrollDTO> getAllPayroll( Integer month) {
        List<Payroll> payrollList = payrollMapper.getAllPayrolls(month);
        TaxInformationExample taxInformationSQL = new TaxInformationExample();
        PayrollExample payrollSQL = new PayrollExample();
        for (Payroll payroll : payrollList) {

            taxInformationSQL.createCriteria().andEmployeeIdEqualTo(payroll.getEmployeeId());
            BigDecimal totalHours = timeTrackingService.sumTotalHoursWorking(payroll.getEmployeeId(), month);
            BigDecimal taxRate = taxInformationMapper.selectByExample(taxInformationSQL).get(0).getTaxRate();
//            System.out.println("totalHours: "+totalHours+" | taxRate: "+ taxRate);
            BigDecimal netSalary = payroll.getSalary()
                    .multiply(totalHours)
                    .multiply(new BigDecimal(100).subtract(taxRate))
                    .divide(new BigDecimal(100));
//            System.out.println("netSalary = " + netSalary);
            payroll.setNetSalary(netSalary);
            payrollSQL.createCriteria().andEmployeeIdEqualTo(payroll.getEmployeeId());
            payrollMapper.updateByExample(payroll, payrollSQL);
            payrollSQL.clear();
        }
        return payrollMapper.getAllSalary(month);
    }

    @Override
    public List<PayrollDTO> getAllPayrollByFirstName(Integer month, String firstname) {
        return payrollMapper.getAllSalaryByFirstName( month,  firstname);
    }

}
