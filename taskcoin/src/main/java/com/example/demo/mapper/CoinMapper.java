package com.example.demo.mapper;

import com.example.demo.model.Coin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CoinMapper {
    List<Coin> findAll();

    Coin findById(long id);

    //
    void deleteById(long id);

    void deleteAll();

    //
    void insert(Coin coin);

    //
    int update(Coin coin);


}