package com.allxone.coinmarket.service;

import com.allxone.coinmarket.dto.response.PayrollDTO;
import com.allxone.coinmarket.model.Payroll;

import java.util.List;

public interface PayrollService {

    List<PayrollDTO> getAllPayroll(Integer month);

    List<PayrollDTO> getAllPayrollByFirstName(Integer month, String firstname);
}
