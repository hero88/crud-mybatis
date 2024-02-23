package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

import java.time.Instant;

@Data
public class TaxInformationRequest {
    private Integer id;
    private Long employeeId;
    private Double taxRate;
    private Boolean taxExemption;
}
