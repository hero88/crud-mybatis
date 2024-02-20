package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.Coin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoinMapper {
    List<Coin> getAllCoins();
    void updateCoin(Coin coin);
    void deleteCoinById(Long id);
    void saveCoin(Coin coin);
    Coin getCoinById(Long id);
    List<Coin> getCoinByUserId(Long id);
}
