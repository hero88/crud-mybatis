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
    public HolidayResponse updateHoliday(HolidayRequest HolidayRequest) {
        Holiday Holiday = HolidayConvert.toHoliday(HolidayRequest);
        holidayMapper.updateHoliday(Holiday);
        return getHolidayById(Holiday.getId());
    }

    @Override
    public void deleteHolidayById(Integer id) {
        holidayMapper.deleteHolidayById(id);
    }

    @Override
    public HolidayResponse saveHoliday(HolidayRequest HolidayRequest) {
        Holiday Holiday = HolidayConvert.toHoliday(HolidayRequest);
        holidayMapper.saveHoliday(Holiday);
        return getHolidayById(Holiday.getId());
    }

    @Override
    public HolidayResponse getHolidayById(Integer id) {
        Holiday Holiday = holidayMapper.getHolidayById(id);
        return HolidayConvert.toDto(Holiday);
    }
}

