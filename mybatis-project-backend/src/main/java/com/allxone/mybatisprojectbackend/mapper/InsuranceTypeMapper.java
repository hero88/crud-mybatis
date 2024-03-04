package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.InsuranceType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuranceTypeMapper {
    List<InsuranceType> getAllInsuranceTypes();
    void updateInsuranceType(InsuranceType insuranceType);
    void deleteInsuranceTypeById(Integer id);
    void saveInsuranceType(InsuranceType insuranceType);
    InsuranceType getInsuranceTypeById(Integer id); 
    List<InsuranceType> getInsuranceTypeByEmployeeId(Long id);
}
