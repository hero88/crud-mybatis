package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.InsuranceTypeRequest;
import com.allxone.mybatisprojectbackend.dto.response.InsuranceTypeResponse;
import com.allxone.mybatisprojectbackend.model.InsuranceType;
import org.springframework.stereotype.Component;

@Component
public class InsuranceTypeConvert {
    public static InsuranceTypeResponse toDto(InsuranceType insuranceType) {
        return InsuranceTypeResponse.builder()
                .id(insuranceType.getId())
                .insuranceName(insuranceType.getInsuranceName())
                .insuranceDescription(insuranceType.getInsuranceDescription())
                .insuranceRate(insuranceType.getInsuranceRate())
                .build();
    }

    public static InsuranceType toInsuranceType(InsuranceTypeRequest insuranceTypeRequest) {
        return InsuranceType.builder()
                .insuranceName(insuranceTypeRequest.getInsuranceName())
                .insuranceDescription(insuranceTypeRequest.getInsuranceDescription())
                .insuranceRate(insuranceTypeRequest.getInsuranceRate())
                .build();
    }
}
