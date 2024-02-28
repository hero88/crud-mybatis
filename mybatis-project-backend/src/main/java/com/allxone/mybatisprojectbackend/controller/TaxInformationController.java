package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.TaxInformationRequest;
import com.allxone.mybatisprojectbackend.dto.response.TaxInformationResponse;
import com.allxone.mybatisprojectbackend.dto.response.TaxInformationResponse;
import com.allxone.mybatisprojectbackend.service.TaxInformationService;
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
@RequestMapping("/api/taxInformation")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class TaxInformationController {

    private final TaxInformationService taxInformationService;

    @GetMapping("/getAllTaxInformation")
    public CommonResponse<List<TaxInformationResponse>> getAllTaxInformation() {
        try {
            List<TaxInformationResponse> data = taxInformationService.getAllTaxInformation();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/saveTaxInformation")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<TaxInformationResponse> saveTaxInformation(@RequestBody TaxInformationRequest taxInformationRequest) {

        try {
            TaxInformationResponse data = taxInformationService.saveTaxInformation(taxInformationRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @DeleteMapping("/deleteTaxInformationById/{id}")
//    @PreAuthorize("hasAuthority('admin:delete')")
    public CommonResponse<Integer> deleteTaxInformationById(@PathVariable Integer id) {

        try {
            taxInformationService.deleteTaxInformationById(id);
            return CommonResponse.success(id);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @GetMapping("/getTaxInformationById/{id}")
    public CommonResponse<TaxInformationResponse> getTaxInformationById(@PathVariable Integer id) {
        try {
            TaxInformationResponse data = taxInformationService.getTaxInformationById(id);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updateTaxInformation")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<TaxInformationResponse> updateTaxInformation(@RequestBody TaxInformationRequest taxInformationRequest) {

        try {
            TaxInformationResponse data = taxInformationService.updateTaxInformation(taxInformationRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }

    }
}
