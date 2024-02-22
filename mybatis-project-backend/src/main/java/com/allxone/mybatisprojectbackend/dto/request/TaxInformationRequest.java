package com.allxone.mybatisprojectbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;

@Data
public class TaxInfomationRequest {
    private Long id;
    private Long employeeId;
    private Instant dateTrack;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime clockIn;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime clockOut;
    private Double totalHours;
}
