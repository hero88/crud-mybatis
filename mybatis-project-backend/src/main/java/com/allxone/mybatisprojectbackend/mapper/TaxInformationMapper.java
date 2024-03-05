package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.Payroll;
import com.allxone.mybatisprojectbackend.model.TaxInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaxInformationMapper {
    List<TaxInformation> getAllTaxInformation();
    void updateTaxInformation(TaxInformation taxInformation);
    void deleteTaxInformationById(Integer id);
    void saveTaxInformation(TaxInformation taxInformation);
    TaxInformation getTaxInformationById(Integer id);
    TaxInformation getTaxInformationByEmployeeId(Long id);
    TaxInformation getTaxInformationByEmployeeIdAndStatus(@Param("id") Long id, @Param("status") boolean status);
}
