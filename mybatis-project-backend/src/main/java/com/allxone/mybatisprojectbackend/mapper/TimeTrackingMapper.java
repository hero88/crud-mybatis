package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.TimeTracking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimeTrackingMapper {
    List<TimeTracking> getAllTimeTracking();
    void updateTimeTracking(TimeTracking timeTracking);
    void deleteTimeTrackingById(Long id);
    void saveTimeTracking(TimeTracking timeTracking);
    TimeTracking getTimeTrackingById(Long id);
}
