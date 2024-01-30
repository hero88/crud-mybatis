package com.allxone.coinmarket.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.allxone.coinmarket.service.CoinService;
import com.allxone.coinmarket.utilities.TimeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService{
	private final TimeUtils timeUntil;
	
	private final String URI_HISTORY_PRICECOIN = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/detail/chart";
	
	@Override
	public Map<String, Object> getHistoryPriceCoin(Integer coinId) {
		RestTemplate restTemplate = new RestTemplate();
		String range=timeUntil.getListTimeStampEnd12MonthsAgo().get(0)+"~"+timeUntil.getListTimeStampEnd12MonthsAgo().get(timeUntil.getListTimeStampEnd12MonthsAgo().size()-1);
		ResponseEntity<String> response = restTemplate
				.getForEntity(URI_HISTORY_PRICECOIN+"?id=" + coinId + "&range="+range, String.class);
		try {
			if (response.getStatusCode().is2xxSuccessful()) {
				String responseData = response.getBody();
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> dataMap;
				dataMap = objectMapper.readValue(responseData, Map.class);
				dataMap.put("timestamps", timeUntil.getListTimeStampEnd12MonthsAgo());
				dataMap.put("months", timeUntil.getEnglishMonthNamesLast12Months());
				return dataMap;
			} else {
				return null;
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
