package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.HolidayConvert;
import com.allxone.mybatisprojectbackend.dto.request.HolidayRequest;
import com.allxone.mybatisprojectbackend.dto.response.HolidayResponse;
import com.allxone.mybatisprojectbackend.mapper.HolidayMapper;
import com.allxone.mybatisprojectbackend.model.Holiday;
import com.allxone.mybatisprojectbackend.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayMapper holidayMapper;

    @Override
    public List<HolidayResponse> getAllHolidays() {
        return holidayMapper.getAllHolidays()
                .stream()
                .map(HolidayConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HolidayResponse updateHoliday(HolidayRequest holidayRequest) {
        Holiday holiday = HolidayConvert.toHoliday(holidayRequest);
        holidayMapper.updateHoliday(holiday);
        return getHolidayById(holiday.getId());
    }

    @Override
    public void deleteHolidayById(Integer id) {
        holidayMapper.deleteHolidayById(id);
    }

    @Override
    public HolidayResponse saveHoliday(HolidayRequest holidayRequest) {
        Holiday holiday = HolidayConvert.toHoliday(holidayRequest);
        holidayMapper.saveHoliday(holiday);
        return getHolidayById(holiday.getId());
    }

    @Override
    public HolidayResponse getHolidayById(Integer id) {
        Holiday holiday = holidayMapper.getHolidayById(id);
        return HolidayConvert.toDto(holiday);
    }
}

