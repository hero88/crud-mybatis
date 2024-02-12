package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.Coin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoinMapper {

    public List<Coin> getAllCoins();
    public void deleteCoin(@Param("id") Long id);

    public void addCoin(Coin coin);

    public void updateCoin(Long id, int quantity);
}
