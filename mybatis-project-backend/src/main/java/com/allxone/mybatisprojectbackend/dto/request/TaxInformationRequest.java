package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class TaxInformationRequest {
    private Integer id;
    private Long employeeId;
    private Double taxRate;
    private Boolean taxExemption;
    private Boolean status;
}
