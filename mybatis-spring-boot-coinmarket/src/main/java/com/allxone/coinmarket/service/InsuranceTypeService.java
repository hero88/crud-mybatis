package com.allxone.coinmarket.service;

import java.util.List;

import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.InsuranceType;

public interface InsuranceTypeService {

	List<InsuranceType> findAll();

	InsuranceType findById(Integer id) throws ParamInvalidException;

	void deleteInsuranceType(Integer id) throws ParamInvalidException;

	void updateInsuranceType(InsuranceType insuranceType) throws ParamInvalidException;

	void addToInsuranceType(InsuranceType insuranceType) throws ParamInvalidException;

}
