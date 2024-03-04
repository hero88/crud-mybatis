package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.dto.response.PayrollDTO;
import com.allxone.coinmarket.mapper.PayrollMapper;
import com.allxone.coinmarket.mapper.TaxInformationMapper;
import com.allxone.coinmarket.model.Payroll;
import com.allxone.coinmarket.model.PayrollExample;
import com.allxone.coinmarket.model.TaxInformationExample;
import com.allxone.coinmarket.service.PayrollService;
import com.allxone.coinmarket.service.TimeTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
            Integer[] id = {Math.toIntExact(payroll.getEmployeeId())};
            Object obj = payrollMapper.calcNetSalary(Arrays.asList(id), month, month, LocalDate.now().getYear(), LocalDate.now().getYear()).get(0);

            taxInformationSQL.createCriteria().andEmployeeIdEqualTo(payroll.getEmployeeId());
            BigDecimal totalHours = timeTrackingService.sumTotalHoursWorking(payroll.getEmployeeId(), month);
            BigDecimal taxRate = taxInformationMapper.selectByExample(taxInformationSQL).get(0).getTaxRate();
            BigDecimal netSalary = ((BigDecimal)((Map<?, ?>) obj).get("Net"));;
            payroll.setNetSalary(netSalary);
            payrollSQL.createCriteria().andEmployeeIdEqualTo(payroll.getEmployeeId());
            payrollMapper.updateByPrimaryKey(payroll);
            payrollSQL.clear();
        }
        return payrollMapper.getAllSalary(month);
    }

    @Override
    public List<PayrollDTO> getAllPayrollByFirstName(Integer month, String firstname) {
        return payrollMapper.getAllSalaryByFirstName( month,  firstname);
    }

    @Override
    public List<Object> getNetSalary(List<Integer> listId,Integer monthFrom, Integer monthTo, Integer yearFrom, Integer yearTo) {
        return payrollMapper.calcNetSalary(listId,monthFrom,monthTo,yearFrom,yearTo);
    }

}
