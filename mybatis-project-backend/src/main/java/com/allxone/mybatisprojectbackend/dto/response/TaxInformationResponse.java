package com.allxone.mybatisprojectbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class TaxInformationResponse {
    private Integer id;
    private Long employeeId;
    private Double taxRate;
    private Boolean taxExemption;
    private Boolean status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDate dateStart;
    private Instant createdAt;
    private Instant updatedAt;
}
