package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.Holiday;
import com.allxone.mybatisprojectbackend.model.InsuranceType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HolidayMapper {
    List<Holiday> getAllHolidays();
    void updateHoliday(Holiday holiday);
    void deleteHolidayById(Integer id);
    void saveHoliday(Holiday holiday);
    Holiday getHolidayById(Integer id);

    List<Holiday> getTotalHolidayByPayrollId(Long id);
}
