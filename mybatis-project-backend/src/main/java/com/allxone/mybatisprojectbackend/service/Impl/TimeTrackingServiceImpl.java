package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.TimeTrackingConvert;
import com.allxone.mybatisprojectbackend.dto.request.TimeTrackingRequest;
import com.allxone.mybatisprojectbackend.dto.request.TimeTrackingRequest;
import com.allxone.mybatisprojectbackend.dto.response.TimeTrackingResponse;
import com.allxone.mybatisprojectbackend.dto.response.TimeTrackingResponse;
import com.allxone.mybatisprojectbackend.mapper.TimeTrackingMapper;
import com.allxone.mybatisprojectbackend.model.TimeTracking;
import com.allxone.mybatisprojectbackend.service.TimeTrackingService;
import com.allxone.mybatisprojectbackend.service.TimeTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TimeTrackingServiceImpl implements TimeTrackingService {

        private final TimeTrackingMapper timeTrackingMapper;
        @Override
        public List<TimeTrackingResponse> getAllTimeTracking() {
            return timeTrackingMapper.getAllTimeTracking()
                    .stream()
                    .map(TimeTrackingConvert::toDto)
                    .collect(Collectors.toList());
        }

        @Override
        public TimeTrackingResponse updateTimeTracking(TimeTrackingRequest timeTrackingRequest) {
            TimeTracking timeTracking = TimeTrackingConvert.toTimeTracking(timeTrackingRequest);
            timeTrackingMapper.updateTimeTracking(timeTracking);
            return getTimeTrackingById(timeTracking.getId());
        }

        @Override
        public void deleteTimeTrackingById(Long id) {
            timeTrackingMapper.deleteTimeTrackingById(id);
        }

        @Override
        public TimeTrackingResponse saveTimeTracking(TimeTrackingRequest timeTrackingRequest) {
            TimeTracking timeTracking = TimeTrackingConvert.toTimeTracking(timeTrackingRequest);
            try {

                timeTrackingMapper.saveTimeTracking(timeTracking);
            }catch (Exception e){
                System.out.println(e);
            }
            return getTimeTrackingById(timeTracking.getId());
        }

        @Override
        public TimeTrackingResponse getTimeTrackingById(Long id) {
            TimeTracking timeTracking = timeTrackingMapper.getTimeTrackingById(id);
            return TimeTrackingConvert.toDto(timeTracking);
        }

    }

