package com.allxone.coinmarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.mapper.HolidaysMapper;
import com.allxone.coinmarket.model.Holidays;
import com.allxone.coinmarket.model.HolidaysExample;
import com.allxone.coinmarket.service.HolidaysService;
import com.allxone.coinmarket.utilities.ValidatorUtils;

@Service
public class HolidaysServiceImpl implements HolidaysService {

	@Autowired
	HolidaysMapper holidaysMapper;

	@Override
	public void addToHoliday(Holidays holidays) throws ParamInvalidException {

		ValidatorUtils.checkNullParam(holidays.getHolidayName(), holidays.getDurationDays());

		holidaysMapper.insert(holidays);
	}

	@Override
	public void updateHoliday(Holidays holidays) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(holidays.getId(), holidays.getHolidayName(), holidays.getDurationDays());

		HolidaysExample holidaysExample = new HolidaysExample();
		holidaysExample.createCriteria().andIdEqualTo(holidays.getId());
		holidaysMapper.updateByExample(holidays, holidaysExample);
	}

	@Override
	public void delete(Integer id) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(id);

		HolidaysExample holidaysExample = new HolidaysExample();
		holidaysExample.createCriteria().andIdEqualTo(id);

		holidaysMapper.deleteByExample(holidaysExample);
	}

	@Override
	public Holidays edit(Integer id) throws ParamInvalidException {
		ValidatorUtils.checkNullParam(id);

		HolidaysExample holidaysExample = new HolidaysExample();
		holidaysExample.createCriteria().andIdEqualTo(id);

		List<Holidays> list = holidaysMapper.selectByExample(holidaysExample);

		if (list != null) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public List<Holidays> getAll() {
		HolidaysExample holidaysExample = new HolidaysExample();

		return holidaysMapper.selectByExample(holidaysExample);
	}

}
