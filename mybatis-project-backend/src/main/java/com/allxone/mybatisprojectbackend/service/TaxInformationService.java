package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.TimeTrackingRequest;
import com.allxone.mybatisprojectbackend.dto.response.TimeTrackingResponse;

import java.util.List;

public interface TimeTrackingService {
    List<TimeTrackingResponse> getAllTimeTracking();
    TimeTrackingResponse updateTimeTracking(TimeTrackingRequest TimeTrackingRequest);
    void deleteTimeTrackingById(Long id);
    TimeTrackingResponse saveTimeTracking(TimeTrackingRequest TimeTrackingRequest);
    TimeTrackingResponse getTimeTrackingById(Long id);
}
