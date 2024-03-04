package com.allxone.mybatisprojectbackend.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class PayrollRequest {
    private Long id;
    private Long employeeId;
    private Double salary;
    private Double bonus;
    private Double deductions;
    private Double netSalary;
    private Short leavePaidDays;
    private JsonNode holidayIds;
    private LocalDate periodStart;
    private LocalDate periodEnd;
}
