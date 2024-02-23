package com.allxone.mybatisprojectbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TimeTracking {
    private Long id;
    private Long employeeId;
    private LocalDate dateTrack;
    private LocalTime clockIn;
    private LocalTime  clockOut;
    private Double totalHours;
    private Instant createdAt;
    private Instant updatedAt;
}
