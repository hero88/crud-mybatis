package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TaxInformationResponse {
    private Integer id;
    private Long employeeId;
    private Double taxRate;
    private Boolean taxExemption;
    private Instant createdAt;
    private Instant updatedAt;
}
