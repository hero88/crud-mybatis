package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.HolidayRequest;
import com.allxone.mybatisprojectbackend.dto.response.HolidayResponse;

import java.util.List;

public interface HolidayService {
    List<HolidayResponse> getAllHolidays();
    HolidayResponse updateHoliday(HolidayRequest holidayRequest);
    void deleteHolidayById(Integer id);
    HolidayResponse saveHoliday(HolidayRequest holidayRequest);
    HolidayResponse getHolidayById(Integer id);
}
