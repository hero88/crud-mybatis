package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.TaxInformationRequest;
import com.allxone.mybatisprojectbackend.dto.response.TaxInformationResponse;

import java.util.List;

public interface TaxInformationService {
    List<TaxInformationResponse> getAllTaxInformation();
    TaxInformationResponse updateTaxInformation(TaxInformationRequest taxInformationRequest);
    void deleteTaxInformationById(Integer id);
    TaxInformationResponse saveTaxInformation(TaxInformationRequest taxInformationRequest);
    TaxInformationResponse getTaxInformationById(Integer id);
}
