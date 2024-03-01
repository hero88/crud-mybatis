package com.allxone.coinmarket.service;

import java.util.List;

import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.Holidays;

public interface HolidaysService {

	List<Holidays> getAll();

	Holidays edit(Integer id) throws ParamInvalidException;

	void delete(Integer id) throws ParamInvalidException;

	void updateHoliday(Holidays holidays) throws ParamInvalidException;

	void addToHoliday(Holidays holidays) throws ParamInvalidException;

}
