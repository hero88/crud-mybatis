package com.allxone.coinmarket.rest;


import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/coin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CoinController {
    private final CoinService coinService;
    @GetMapping
    public ResponseEntity<?> getAllCoins(@RequestParam(defaultValue = "100") Integer limit) throws IOException {
        return ResponseEntity.ok(ApiResponse.builder().message("ok").success(true).data(coinService.fetchApiDataCoins(limit)).build());
    }

    @GetMapping("myCoins")
    public ResponseEntity<?> getCoinsUser() throws IOException {
        return ResponseEntity.ok(ApiResponse.builder().data(coinService.getAllCoinsUser()).success(true).message("ok").build());
    }
}
