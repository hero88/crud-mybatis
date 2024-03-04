package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.HolidayRequest;
import com.allxone.mybatisprojectbackend.dto.response.HolidayResponse;
import com.allxone.mybatisprojectbackend.service.HolidayService;
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
@RequestMapping("/api/holiday")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping("/getAllHoliday")
    public CommonResponse<List<HolidayResponse>> getAllHolidays() {
        try {
            List<HolidayResponse> data = holidayService.getAllHolidays();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/saveHoliday")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<HolidayResponse> saveHoliday(@RequestBody HolidayRequest holidayRequest) {

        try {
            HolidayResponse data = holidayService.saveHoliday(holidayRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @GetMapping("/getHolidayById/{id}")
    public CommonResponse<HolidayResponse> getHolidayById(@PathVariable Integer id) {
        try {
            HolidayResponse data = holidayService.getHolidayById(id);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updateHoliday")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<HolidayResponse> updateHoliday(@RequestBody HolidayRequest holidayRequest) {

        try {
            HolidayResponse data = holidayService.updateHoliday(holidayRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }
    }

    @DeleteMapping("/deleteHolidayById/{id}")
//    @PreAuthorize("hasAuthority('admin:delete')")
    public CommonResponse<Integer> deleteHolidayById(@PathVariable Integer id) {

        try {
            holidayService.deleteHolidayById(id);
            return CommonResponse.success(id);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }
}
