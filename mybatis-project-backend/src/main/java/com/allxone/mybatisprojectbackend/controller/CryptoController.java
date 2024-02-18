package com.allxone.mybatisprojectbackend.controller;


import com.allxone.mybatisprojectbackend.dto.request.CoinDetailRequest;
import com.allxone.mybatisprojectbackend.dto.request.CryptoCurrencyRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinDetailResponse;
import com.allxone.mybatisprojectbackend.dto.response.CryptoCurrencyDataResponse;
import com.allxone.mybatisprojectbackend.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping("/coinmarketcap")
    public CryptoCurrencyDataResponse getCoinMarketCapData(@RequestBody(required = false) CryptoCurrencyRequest request){
        return cryptoService.getCoinMarketCapData(request);
    };

    @GetMapping("/detail")
    public CoinDetailResponse getCoinMarketCapDetailData(@RequestBody CoinDetailRequest request){
        return cryptoService.getCoinMarketCapDetailData(request);
    };
}
