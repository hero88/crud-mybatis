package com.allxone.coinmarket.rest;

import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.dto.response.EmployeeDTO;
import com.allxone.coinmarket.dto.response.TimeTrackingDTO;
import com.allxone.coinmarket.dto.response.WorkingTimeDTO;
import com.allxone.coinmarket.model.TimeTracking;
import com.allxone.coinmarket.service.EmployeesService;
import com.allxone.coinmarket.service.TimeTrackingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/time-tracking")
@RequiredArgsConstructor
public class TimeTrackingApi {
    private final TimeTrackingService timeTrackingService;

    private final ModelMapper mapDTO;

    private final EmployeesService employeesService;

    @PostMapping("")
    public ResponseEntity<?> createTimeTracking(@RequestBody TimeTracking tracking) {
        TimeTracking timeTracking = timeTrackingService.save(tracking);
        if (timeTracking != null) {
            TimeTrackingDTO dto = mapDTO.map(timeTracking, TimeTrackingDTO.class);
            if (dto != null) {
                return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                        .message("successful")
                        .success(true)
                        .data(dto)
                        .build());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("Save time tracking failure!")
                .success(false)
                .build());
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteTimeTracking(@RequestParam("id") Long id) {
        TimeTracking timeTracking = timeTrackingService.delete(id);
        if (timeTracking != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .data(timeTracking)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message("Delete time tracking failure!")
                .success(false)
                .build());
    }

    @GetMapping("")
    public ResponseEntity<?> getTimeTrackingDetail(@RequestParam("id") Long id) {
        TimeTracking timeTracking = timeTrackingService.getInformation(id);
        if (timeTracking != null) {
            TimeTrackingDTO dto = mapDTO.map(timeTracking, TimeTrackingDTO.class);
            if (dto != null) {
                return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                        .message("successful")
                        .success(true)
                        .data(dto)
                        .build());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message("Get detail information tracking failure!")
                .success(false)
                .build());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListTimeTrackingByDate(@RequestParam("date") Date date) {
        List<TimeTracking> list = timeTrackingService.getListByDate(date);
        List<TimeTrackingDTO> dto = list.stream().map(e -> mapDTO.map(e, TimeTrackingDTO.class)).collect(Collectors.toList());
        if (list != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .data(dto)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message("Don't have list time tracking in this date!")
                .success(false)
                .build());
    }

    @GetMapping("/list-all-time")
    public ResponseEntity<?> getListTimeTrackingByDate(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<WorkingTimeDTO> list = timeTrackingService.getAllWorkingTimeEmployeeAllTime(limit);
        if (list != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .data(list)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message("Don't have list time tracking in this date!")
                .success(false)
                .build());
    }

    @GetMapping("/list-working")
    public ResponseEntity<?> getTimeTrackingByDate(@RequestParam("date") Date date, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<WorkingTimeDTO> list = timeTrackingService.getAllWorkingTimeEmployee(date, limit);
        if (list != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .data(list)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("Don't have list time tracking in this date!")
                .success(false)
                .build());
    }

    @GetMapping("/list-employee")
    public ResponseEntity<?> getListEmployee() {
        List<EmployeeDTO> list = employeesService.findAllEmployeeNotTermination();
        if (list != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .data(list)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("Don't have list time tracking in this date!")
                .success(false)
                .build());
    }

    @GetMapping("/list-working/filter")
    public ResponseEntity<?> getTimeTrackingByFilter(@RequestParam("listId") String listId, @RequestParam("from") Date from,
                                                     @RequestParam("to") Date to, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<Integer> listID = Arrays.stream(listId.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
        List<WorkingTimeDTO> list = timeTrackingService.getAllWorkingTimeEmployeeByFilter(listID,from,to,limit);
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .data(list)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("No data")
                .success(false)
                .build());
    }

}
