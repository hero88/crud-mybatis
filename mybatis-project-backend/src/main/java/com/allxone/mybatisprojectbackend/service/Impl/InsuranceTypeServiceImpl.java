package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.InsuranceTypeConvert;
import com.allxone.mybatisprojectbackend.dto.request.InsuranceTypeRequest;
import com.allxone.mybatisprojectbackend.dto.response.InsuranceTypeResponse;
import com.allxone.mybatisprojectbackend.mapper.InsuranceTypeMapper;
import com.allxone.mybatisprojectbackend.model.InsuranceType;
import com.allxone.mybatisprojectbackend.service.InsuranceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsuranceTypeServiceImpl implements InsuranceTypeService {

    private final InsuranceTypeMapper insuranceTypeMapper;

    @Override
    public List<InsuranceTypeResponse> getAllInsuranceTypes() {
        return insuranceTypeMapper.getAllInsuranceTypes()
                .stream()
                .map(InsuranceTypeConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InsuranceTypeResponse updateInsuranceType(InsuranceTypeRequest insuranceTypeRequest) {
        InsuranceType insuranceType = InsuranceTypeConvert.toInsuranceType(insuranceTypeRequest);
        insuranceTypeMapper.updateInsuranceType(insuranceType);
        return getInsuranceTypeById(insuranceType.getId());
    }

    @Override
    public void deleteInsuranceTypeById(Integer id) {
        insuranceTypeMapper.deleteInsuranceTypeById(id);
    }

    @Override
    public InsuranceTypeResponse saveInsuranceType(InsuranceTypeRequest insuranceTypeRequest) {
        InsuranceType insuranceType = InsuranceTypeConvert.toInsuranceType(insuranceTypeRequest);
        insuranceTypeMapper.saveInsuranceType(insuranceType);
        return getInsuranceTypeById(insuranceType.getId());
    }

    @Override
    public InsuranceTypeResponse getInsuranceTypeById(Integer id) {
        InsuranceType insuranceType = insuranceTypeMapper.getInsuranceTypeById(id);
        return InsuranceTypeConvert.toDto(insuranceType);
    }
}

