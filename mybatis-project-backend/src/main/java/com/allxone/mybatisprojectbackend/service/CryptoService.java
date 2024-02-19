package com.allxone.mybatisprojectbackend.service;


import com.allxone.mybatisprojectbackend.dto.request.CoinDetailRequest;
import com.allxone.mybatisprojectbackend.dto.request.CryptoCurrencyRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinChartDataResponse;
import com.allxone.mybatisprojectbackend.dto.response.CoinDetailResponse;
import com.allxone.mybatisprojectbackend.dto.response.CryptoCurrencyDataResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface CryptoService {
    CryptoCurrencyDataResponse getCoinMarketCapData(CryptoCurrencyRequest request);
    CoinChartDataResponse getCoinMarketCapDetailData(CoinDetailRequest request);
}
