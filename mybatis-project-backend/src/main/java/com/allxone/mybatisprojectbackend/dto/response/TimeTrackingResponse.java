package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;

@Data
public class TimeTrackingRequest {
    private Long id;
    private Long employeeId;
    private Instant dateTrack;
    private LocalTime clockIn;
    private LocalTime  clockOut;
    private Double totalHours;
}
