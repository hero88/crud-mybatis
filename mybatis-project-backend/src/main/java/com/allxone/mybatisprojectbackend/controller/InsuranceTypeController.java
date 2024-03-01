package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.InsuranceTypeRequest;
import com.allxone.mybatisprojectbackend.dto.response.InsuranceTypeResponse;
import com.allxone.mybatisprojectbackend.service.InsuranceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/InsuranceType")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class InsuranceTypeController {

    private final InsuranceTypeService InsuranceTypeService;

    @GetMapping("/getAllInsuranceType")
    public CommonResponse<List<InsuranceTypeResponse>> getAllInsuranceTypes() {
        try {
            List<InsuranceTypeResponse> data = InsuranceTypeService.getAllInsuranceTypes();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/saveInsuranceType")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<InsuranceTypeResponse> saveInsuranceType(@RequestBody InsuranceTypeRequest insuranceTypeRequest) {

        try {
            InsuranceTypeResponse data = InsuranceTypeService.saveInsuranceType(insuranceTypeRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @GetMapping("/getInsuranceTypeById/{id}")
    public CommonResponse<InsuranceTypeResponse> getInsuranceTypeById(@PathVariable Integer id) {
        try {
            InsuranceTypeResponse data = InsuranceTypeService.getInsuranceTypeById(id);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updateInsuranceType")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<InsuranceTypeResponse> updateInsuranceType(@RequestBody InsuranceTypeRequest insuranceTypeRequest) {

        try {
            InsuranceTypeResponse data = InsuranceTypeService.updateInsuranceType(insuranceTypeRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }
    }
}

