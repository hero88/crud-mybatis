package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.TimeTrackingRequest;
import com.allxone.mybatisprojectbackend.dto.response.DepartmentResponse;
import com.allxone.mybatisprojectbackend.dto.response.TimeTrackingResponse;
import com.allxone.mybatisprojectbackend.service.TimeTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/timeTracking")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class TimeTrackingController {

    private final TimeTrackingService timeTrackingService;

    @GetMapping("/getAllTimeTracking")
    public CommonResponse<List<TimeTrackingResponse>> getAllTimeTracking() {
        try {
            List<TimeTrackingResponse> data = timeTrackingService.getAllTimeTracking();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/saveTimeTracking")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<TimeTrackingResponse> saveTimeTracking(@RequestBody TimeTrackingRequest timeTrackingRequest) {

        try {
            TimeTrackingResponse data = timeTrackingService.saveTimeTracking(timeTrackingRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @GetMapping("/getTimeTrackingById/{id}")
    public CommonResponse<TimeTrackingResponse> getTimeTrackingById(@PathVariable Long id) {
        try {
            TimeTrackingResponse data = timeTrackingService.getTimeTrackingById(id);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updateTimeTracking")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<TimeTrackingResponse> updateTimeTracking(@RequestBody TimeTrackingRequest timeTrackingRequest) {

        try {
            TimeTrackingResponse data = timeTrackingService.updateTimeTracking(timeTrackingRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }
    }
}
