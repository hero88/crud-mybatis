package com.allxone.coinmarket.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.service.CoinService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class CoinRestApi {
	
	private final CoinService coinService;
	
	@GetMapping("/api/v1/history-coin")
	public ResponseEntity<?> getHistory(@RequestParam("id") Integer id) {
		Map<String, Object> history = coinService.getHistoryPriceCoin(id);
		if(history!=null) {
			return ResponseEntity.ok(history);
		}else {
			return ResponseEntity.badRequest().body(ApiResponse.builder().message("Get data failure").success(false).data(null));
		}	
	}
}
