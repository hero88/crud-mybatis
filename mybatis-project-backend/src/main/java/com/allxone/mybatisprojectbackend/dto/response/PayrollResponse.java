package com.allxone.mybatisprojectbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class PayrollResponse {
    private Long id;
    private Long employeeId;
    private Double salary;
    private Double bonus;
    private Double deductions;
    private Double netSalary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDate periodStart;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)

    private LocalDate periodEnd;
  
    private Short leavePaidDays;
    private JsonNode holidayIds;
    private Instant createdAt;
    private Instant updatedAt;
}
