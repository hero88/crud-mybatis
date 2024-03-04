package com.allxone.coinmarket.rest;

import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.dto.response.PayrollDTO;
import com.allxone.coinmarket.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/payroll")
public class PayrollRestApi {

    private final PayrollService payrollService;

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
    public ResponseEntity<?> getAllPayrollByFirstName(@PathVariable(value = "month") Integer month, @RequestParam(value = "firstname", defaultValue = "", required = false) String firstname) {
        List<PayrollDTO> payrollList = payrollService.getAllPayrollByFirstName(month, firstname);
        if (!payrollList.isEmpty()) {
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

    @GetMapping("/net-salary")
    public ResponseEntity<?> getNetSalary(@RequestParam(value = "list", defaultValue = "1") String listId, @RequestParam(value = "monthFrom", defaultValue = "1") Integer monthFrom, @RequestParam(value = "monthTo", defaultValue = "12") Integer monthTo,
                                          @RequestParam(value = "yearFrom", defaultValue = "2024") Integer yearFrom, @RequestParam(value = "yearTo", defaultValue = "2024") Integer yearTo) {
        List<Object> list = payrollService.getNetSalary(Arrays.stream(listId.split(",")).map(Integer::parseInt).collect(Collectors.toList()), monthFrom, monthTo, yearFrom, yearTo);
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("Successfully")
                    .success(true)
                    .data(list)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("Employee dose not exist")
                .success(false)
                .data(list)
                .build());
    }

}
