package com.allxone.coinmarket.rest;

import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.dto.response.PayrollDTO;
import com.allxone.coinmarket.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/payroll")
public class PayrollRestApi {

    private  final PayrollService payrollService;

    @GetMapping()
    public ResponseEntity<?> getAllPayroll(@RequestParam(value = "month", defaultValue = "1") Integer month) {
        List<PayrollDTO> payrollList = payrollService.getAllPayroll(month);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("Successfully")
                .success(true)
                .data(payrollList)
                .build());
    }

    @GetMapping("/{month}")
    public ResponseEntity<?> getAllPayrollByFirstName(@PathVariable(value = "month") Integer month, @RequestParam(value = "firstname",defaultValue = "", required = false) String firstname) {
        List<PayrollDTO> payrollList = payrollService.getAllPayrollByFirstName(month,firstname);
        if (!payrollList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("Successfully")
                    .success(true)
                    .data(payrollList)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("Employee dose not exist")
                .success(false)
                .data(payrollList)
                .build());
    }

}
