package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class HolidayResponse {
    private Integer id;
    private String holidayName;
    private String holidayDescription;
    private Short durationDays;
}
