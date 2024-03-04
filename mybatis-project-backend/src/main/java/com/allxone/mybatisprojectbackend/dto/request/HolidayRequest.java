package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

@Data
public class HolidayRequest {
    private Integer id;
    private String holidayName;
    private String holidayDescription;
    private Short durationDays;
}
