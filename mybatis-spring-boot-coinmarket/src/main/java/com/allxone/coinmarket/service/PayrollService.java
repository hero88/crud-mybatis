package com.allxone.coinmarket.service;

import java.util.List;

import com.allxone.coinmarket.dto.response.PayrollDTO;

public interface PayrollService {

    List<PayrollDTO> getAllPayroll(Integer month);

    List<PayrollDTO> getAllPayrollByFirstName(Integer month, String firstname);

}