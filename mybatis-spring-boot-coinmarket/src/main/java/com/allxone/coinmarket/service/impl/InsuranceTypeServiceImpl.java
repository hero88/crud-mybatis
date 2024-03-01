package com.allxone.coinmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.mapper.InsuranceTypeMapper;
import com.allxone.coinmarket.model.InsuranceType;
import com.allxone.coinmarket.model.InsuranceTypeExample;
import com.allxone.coinmarket.service.InsuranceTypeService;
import com.allxone.coinmarket.utilities.ValidatorUtils;

@Service
public class InsuranceTypeServiceImpl implements InsuranceTypeService {

	@Autowired
	InsuranceTypeMapper insuranceTypeMapper;

	@Override
	public void addToInsuranceType(InsuranceType insuranceType) throws ParamInvalidException {

		ValidatorUtils.checkNullParam(insuranceType.getInsuranceName(), insuranceType.getInsuranceRate());

		insuranceTypeMapper.insert(insuranceType);
	}

	@Override
	public void updateInsuranceType(InsuranceType insuranceType) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(insuranceType.getId(), insuranceType.getInsuranceName(),
				insuranceType.getInsuranceRate());

		InsuranceTypeExample insuranceTypeExample = new InsuranceTypeExample();
		insuranceTypeExample.createCriteria().andIdEqualTo(insuranceType.getId());

		insuranceTypeMapper.updateByExample(insuranceType, insuranceTypeExample);
	}

	@Override
	public void deleteInsuranceType(Integer id) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(id);

		InsuranceTypeExample insuranceTypeExample = new InsuranceTypeExample();
		insuranceTypeExample.createCriteria().andIdEqualTo(id);

		insuranceTypeMapper.deleteByExample(insuranceTypeExample);
	}

	@Override
	public InsuranceType findById(Integer id) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(id);

		InsuranceTypeExample insuranceTypeExample = new InsuranceTypeExample();
		insuranceTypeExample.createCriteria().andIdEqualTo(id);

		List<InsuranceType> list = insuranceTypeMapper.selectByExample(insuranceTypeExample);

		if (list != null) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public List<InsuranceType> findAll() {
		InsuranceTypeExample insuranceTypeExample = new InsuranceTypeExample();

		return insuranceTypeMapper.selectByExample(insuranceTypeExample);
	}

}
