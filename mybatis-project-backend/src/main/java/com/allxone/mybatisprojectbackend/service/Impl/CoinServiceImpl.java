package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.dto.AddCoinRequest;
import com.allxone.mybatisprojectbackend.mapper.CoinMapper;
import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    CoinMapper coinMapper;

    @Override
    public List<Coin> getAllCoins() {
        return coinMapper.getAllCoins();
    }

    @Override
    public void deleteCoin(Long id) {
        coinMapper.deleteCoin(id);
    }

    @Override
    public void addCoin(AddCoinRequest newCoin) {

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        Coin coin = Coin.builder()
                .name(newCoin.getName())
                .symbol(newCoin.getSymbol())
                .coinMarketId(newCoin.getCoinMarketId())
                .quantity(1)
                .userId(newCoin.getUserId())
                .createdAt(currentTimestamp)
                .updatedAt(currentTimestamp)
                .build();
        coinMapper.addCoin(coin);
    }

    @Override
    public void updateCoin(Long id, int quantity) {
        coinMapper.updateCoin(id, quantity);
    }
}
