package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

@Data
public class InsuranceTypeRequest {
    private Integer id;
    private String insuranceName;
    private String insuranceDescription;
    private Double insuranceRate;
}
