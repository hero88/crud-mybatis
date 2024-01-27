package com.allxone.mybatisprojectservice.service;

import com.allxone.mybatisprojectservice.dto.coin.CoinDTO;
import com.allxone.mybatisprojectservice.model.Coins;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICoinService {
    Coins findById(Long id);

    List<CoinDTO> findAllCoinByUserId(Long userId);

    @Transactional
    void updateCoin(Coins coins);

    @Transactional
    void insertCoin(Coins coins);
}
