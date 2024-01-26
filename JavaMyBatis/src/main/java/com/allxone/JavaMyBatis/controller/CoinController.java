package com.allxone.JavaMyBatis.controller;

import com.allxone.JavaMyBatis.dto.response.ApiResponse;
import com.allxone.JavaMyBatis.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/coin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CoinController {
    private final CoinService coinService;
    @GetMapping
    public ResponseEntity<?> getAllCoins() throws IOException {
        return ResponseEntity.ok(ApiResponse.builder().message("ok").success(true).data(coinService.fetchApiDataCoins()).build());
    }
}
