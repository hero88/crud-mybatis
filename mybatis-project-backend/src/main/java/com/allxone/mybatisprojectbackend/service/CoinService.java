package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.model.Coin;

import java.util.List;

public interface CoinService {
    List<Coin> getAllCoins();
    Coin updateCoin(Coin coin);
    void deleteCoinById(Long id);
    Coin saveCoin(Coin coin);
    Coin getCoinById(Long id);
}
