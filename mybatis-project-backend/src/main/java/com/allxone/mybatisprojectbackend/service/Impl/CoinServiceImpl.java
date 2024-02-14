package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.mapper.CoinMapper;
import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService {

    private final CoinMapper coinMapper;
    @Override
    public List<Coin> getAllCoins() {
        return coinMapper.getAllCoins();
    }

    @Override
    public Coin updateCoin(Coin coin) {
        coinMapper.updateCoin(coin);
        return getCoinById(coin.getId());
    }

    @Override
    public void deleteCoinById(Long id) {
        coinMapper.deleteCoinById(id);
    }

    @Override
    public Coin saveCoin(Coin coin) {
        coinMapper.saveCoin(coin);
        return getCoinById(coin.getId());
    }

    @Override
    public Coin getCoinById(Long id) {
        return coinMapper.getCoinById(id);
    }
}
