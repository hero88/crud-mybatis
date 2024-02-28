package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.TaxInformationRequest;
import com.allxone.mybatisprojectbackend.dto.response.TaxInformationResponse;
import com.allxone.mybatisprojectbackend.model.TaxInformation;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TaxInformationConvert {

    public static TaxInformationResponse toDto(TaxInformation taxInformation) {
        return TaxInformationResponse.builder()
                .id(taxInformation.getId())
                .employeeId(taxInformation.getEmployeeId())
                .taxRate(taxInformation.getTaxRate())
                .taxExemption(taxInformation.getTaxExemption())
                .createdAt(taxInformation.getCreatedAt())
                .updatedAt(taxInformation.getUpdatedAt())
                .build();
    }

    public static TaxInformation toTaxInformation(TaxInformationRequest taxInformationRequest) {
        return TaxInformation.builder()
                .id(taxInformationRequest.getId())
                .employeeId(taxInformationRequest.getEmployeeId())
                .taxRate(taxInformationRequest.getTaxRate())
                .taxExemption(taxInformationRequest.getTaxExemption())
                .build();
    }
}
