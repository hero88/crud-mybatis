package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class InsuranceTypeResponse {
    private Integer id;
    private String insuranceName;
    private String insuranceDescription;
    private Double insuranceRate;
}
