package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.TimeTrackingRequest;
import com.allxone.mybatisprojectbackend.dto.response.TimeTrackingResponse;

import java.util.List;

public interface TimeTrackingService {
    List<TimeTrackingResponse> getAllTimeTracking();
    TimeTrackingResponse updateTimeTracking(TimeTrackingRequest timeTrackingRequest);
    void deleteTimeTrackingById(Long id);
    TimeTrackingResponse saveTimeTracking(TimeTrackingRequest timeTrackingRequest);
    TimeTrackingResponse getTimeTrackingById(Long id);
}
