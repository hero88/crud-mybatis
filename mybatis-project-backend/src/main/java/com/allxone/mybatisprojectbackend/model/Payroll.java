package com.allxone.mybatisprojectbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payroll {
    private Long id;
    private Long employeeId;
    private Double salary;
    private Double bonus;
    private Double deductions;
    private Double netSalary;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private Instant createdAt;
    private Instant updatedAt;
}
