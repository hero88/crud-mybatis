package com.allxone.coinmarket.service;

import java.util.List;
import java.util.Map;

import com.allxone.coinmarket.dto.response.PayrollDTO;
import com.allxone.coinmarket.model.Payroll;

public interface PayrollService {

    List<PayrollDTO> getAllPayroll(Integer month);

    List<PayrollDTO> getAllPayrollByFirstName(Integer month, String firstname);

    List<Object> getNetSalary(List<Integer> listId,Integer monthFrom, Integer monthTo,Integer yearFrom,Integer yearTo);

    Payroll getPayrollById(Long id);

    Payroll updatePayrollById(Payroll payroll);
}