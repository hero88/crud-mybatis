package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.TaxInformationConvert;
import com.allxone.mybatisprojectbackend.dto.request.TaxInformationRequest;
import com.allxone.mybatisprojectbackend.dto.response.TaxInformationResponse;
import com.allxone.mybatisprojectbackend.mapper.TaxInformationMapper;
import com.allxone.mybatisprojectbackend.model.TaxInformation;
import com.allxone.mybatisprojectbackend.service.TaxInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaxInformationServiceImpl implements TaxInformationService {

    private final TaxInformationMapper taxInformationMapper;

    @Override
    public List<TaxInformationResponse> getAllTaxInformation() {
        return taxInformationMapper.getAllTaxInformation()
                .stream()
                .map(TaxInformationConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaxInformationResponse updateTaxInformation(TaxInformationRequest taxInformationRequest) {
        TaxInformation TaxInformation = TaxInformationConvert.toTaxInformation(taxInformationRequest);
        taxInformationMapper.updateTaxInformation(TaxInformation);
        return getTaxInformationById(TaxInformation.getId());
    }

    @Override
    public void deleteTaxInformationById(Integer id) {
        taxInformationMapper.deleteTaxInformationById(id);
    }

    @Override
    public TaxInformationResponse saveTaxInformation(TaxInformationRequest taxInformationRequest) {
        TaxInformation TaxInformation = TaxInformationConvert.toTaxInformation(taxInformationRequest);
        taxInformationMapper.saveTaxInformation(TaxInformation);
        return getTaxInformationById(TaxInformation.getId());
    }

    @Override
    public TaxInformationResponse getTaxInformationById(Integer id) {
        TaxInformation TaxInformation = taxInformationMapper.getTaxInformationById(id);
        return TaxInformationConvert.toDto(TaxInformation);
    }

}

