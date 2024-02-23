package com.allxone.mybatisprojectbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TimeTrackingRequest {
    private Long id;
    private Long employeeId;
    private LocalDate dateTrack;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime clockIn;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime clockOut;
    private Double totalHours;
}
