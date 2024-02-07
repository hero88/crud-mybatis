package com.allxone.mybatisprojectbackend.service;


import com.allxone.mybatisprojectbackend.dto.request.CryptoCurrencyRequest;
import com.allxone.mybatisprojectbackend.dto.response.CryptoCurrencyDataResponse;

public interface CryptoService {
    CryptoCurrencyDataResponse getCoinMarketCapData(CryptoCurrencyRequest request);
}
