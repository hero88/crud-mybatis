package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.InsuranceTypeRequest;
import com.allxone.mybatisprojectbackend.dto.response.InsuranceTypeResponse;

import java.util.List;

public interface InsuranceTypeService {
    List<InsuranceTypeResponse> getAllInsuranceTypes();
    InsuranceTypeResponse updateInsuranceType(InsuranceTypeRequest insuranceTypeRequest);
    void deleteInsuranceTypeById(Integer id);
    InsuranceTypeResponse saveInsuranceType(InsuranceTypeRequest insuranceTypeRequest);
    InsuranceTypeResponse getInsuranceTypeById(Integer id);
}
