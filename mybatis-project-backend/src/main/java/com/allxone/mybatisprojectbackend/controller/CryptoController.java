package com.allxone.mybatisprojectbackend.controller;


import com.allxone.mybatisprojectbackend.dto.request.CryptoCurrencyRequest;
import com.allxone.mybatisprojectbackend.dto.response.CryptoCurrencyDataResponse;
import com.allxone.mybatisprojectbackend.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping("/coinmarketcap")
    public CryptoCurrencyDataResponse getCoinMarketCapData(CryptoCurrencyRequest request){
        return cryptoService.getCoinMarketCapData(request);
    };
}
