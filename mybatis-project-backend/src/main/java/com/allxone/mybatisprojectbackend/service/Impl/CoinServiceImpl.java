package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.CoinConvert;
import com.allxone.mybatisprojectbackend.dto.request.CoinRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinResponse;
import com.allxone.mybatisprojectbackend.mapper.CoinMapper;
import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService {

    private final CoinMapper coinMapper;
    @Override
    public List<CoinResponse> getAllCoins() {
        return coinMapper.getAllCoins()
        .stream()
        .map(CoinConvert::toDto)
        .collect(Collectors.toList());
    }

    @Override
    public CoinResponse updateCoin(CoinRequest coinRequest) {
        Coin coin = CoinConvert.toCoin(coinRequest);
        coinMapper.updateCoin(coin);
        return getCoinById(coin.getId());
    }

    @Override
    public void deleteCoinById(Long id) {
        coinMapper.deleteCoinById(id);
    }

    @Override
    public CoinResponse saveCoin(CoinRequest coinRequest) {
        Coin coin = CoinConvert.toCoin(coinRequest);
        coinMapper.saveCoin(coin);
        return getCoinById(coin.getId());
    }

    @Override
    public CoinResponse getCoinById(Long id) {
        Coin coin = coinMapper.getCoinById(id);
        return CoinConvert.toDto(coin);
    }

    @Override
    public List<CoinResponse> getCoinByUserId(Long id) {
        return coinMapper.getCoinByUserId(id).stream()
                .map(CoinConvert::toDto)
                .collect(Collectors.toList());
    }

}
