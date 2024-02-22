package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.TimeTrackingRequest;
import com.allxone.mybatisprojectbackend.dto.response.TimeTrackingResponse;
import com.allxone.mybatisprojectbackend.model.TimeTracking;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalTime;

@Component
public class TimeTrackingConvert {

    public static TimeTrackingResponse toDto(TimeTracking timeTracking) {
        return TimeTrackingResponse.builder()
                .id(timeTracking.getId())
                .employeeId(timeTracking.getEmployeeId())
                .dateTrack(timeTracking.getDateTrack())
                .clockIn(timeTracking.getClockIn())
                .clockOut(timeTracking.getClockOut())
                .totalHours(timeTracking.getTotalHours())
                .createdAt(timeTracking.getCreatedAt())
                .updatedAt(timeTracking.getUpdatedAt())
                .build();
    }

    public static TimeTracking toTimeTracking(TimeTrackingRequest timeTrackingRequest) {
        return TimeTracking.builder()
                .id(timeTrackingRequest.getId())
                .employeeId(timeTrackingRequest.getEmployeeId())
                .dateTrack(timeTrackingRequest.getDateTrack())
                .clockIn(timeTrackingRequest.getClockIn())
                .clockOut(timeTrackingRequest.getClockOut())
                .totalHours(timeTrackingRequest.getTotalHours())
                .build();
    }
}
