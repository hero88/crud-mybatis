package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.CoinRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinResponse;
import com.allxone.mybatisprojectbackend.model.Coin;

import java.util.List;

public interface CoinService {
    List<CoinResponse> getAllCoins();
    CoinResponse updateCoin(CoinRequest coinRequest);
    void deleteCoinById(Long id);
    CoinResponse saveCoin(CoinRequest coinRequest);
    CoinResponse getCoinById(Long id);
    List<CoinResponse> getCoinByUserId(Long id);
}
