package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.AddCoinRequest;
import com.allxone.mybatisprojectbackend.model.Coin;

import java.util.List;

public interface CoinService {

    public List<Coin> getAllCoins();
    public void deleteCoin(Long id);

    public void addCoin(AddCoinRequest newCoin);

    public void updateCoin(Long id, int quantity);

    public List<Coin> getCoinsByUserId(Long userId);
}
