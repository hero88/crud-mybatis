package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.client.CryptoClient;
import com.allxone.mybatisprojectbackend.dto.request.CoinDetailRequest;
import com.allxone.mybatisprojectbackend.dto.request.CryptoCurrencyRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinDetailResponse;
import com.allxone.mybatisprojectbackend.dto.response.CryptoCurrencyDataResponse;
import com.allxone.mybatisprojectbackend.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final CryptoClient cryptoClient;
    @Override
    public CryptoCurrencyDataResponse getCoinMarketCapData(CryptoCurrencyRequest request) {
        return cryptoClient.getCoinMarketCapData(request);
    }

    @Override
    public CoinDetailResponse getCoinMarketCapDetailData(CoinDetailRequest request) {
        return cryptoClient.getCoinMarketCapDetailData(request.getId(),request.getRange());
    }
}
