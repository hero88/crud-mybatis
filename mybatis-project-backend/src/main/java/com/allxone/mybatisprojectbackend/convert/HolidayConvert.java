package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.HolidayRequest;
import com.allxone.mybatisprojectbackend.dto.response.HolidayResponse;
import com.allxone.mybatisprojectbackend.model.Holiday;
import org.springframework.stereotype.Component;

@Component
public class HolidayConvert {
    public static HolidayResponse toDto(Holiday holiday) {
        return HolidayResponse.builder()
                .id(holiday.getId())
                .holidayName(holiday.getHolidayName())
                .holidayDescription(holiday.getHolidayDescription())
                .durationDays(holiday.getDurationDays())
                .build();
    }

    public static Holiday toHoliday(HolidayRequest holidayRequest) {
        return Holiday.builder()
                .id(holidayRequest.getId())
                .holidayName(holidayRequest.getHolidayName())
                .holidayDescription(holidayRequest.getHolidayDescription())
                .durationDays(holidayRequest.getDurationDays())
                .build();
    }
}
