package com.allxone.mybatisprojectbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaxInformation {
    private Integer id;
    private Long employeeId;
    private Double taxRate;
    private Boolean taxExemption;
    private Instant createdAt;
    private Instant updatedAt;
}
