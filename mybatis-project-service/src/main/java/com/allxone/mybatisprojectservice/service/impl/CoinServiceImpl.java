package com.allxone.mybatisprojectservice.service.impl;

import com.allxone.mybatisprojectservice.dto.coin.CoinDTO;
import com.allxone.mybatisprojectservice.exception.NotFoundResourceException;
import com.allxone.mybatisprojectservice.model.Coins;
import com.allxone.mybatisprojectservice.mapper.CoinRepository;
import com.allxone.mybatisprojectservice.service.ICoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements ICoinService {

    private final CoinRepository coinRepository;

    @Override
    public Coins findById(Long id) {
        return coinRepository.findById(id);
    }

    @Override
    public List<CoinDTO> findAllCoinByUserId(Long userId) {
        List<Coins> coins = coinRepository.findAllCoinByUserId(userId);
        if(coins.isEmpty()) {
            throw new NotFoundResourceException("Can not find list coin by id: " + userId);
        }
        return coins.stream()
                .map(Coins::toCoinDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteCoinById(Long id) {
        coinRepository.deleteCoinById(id);
    }

    @Transactional
    @Override
    public void updateCoin(Coins coins) {
        coinRepository.updateCoin(coins);
    }

    @Transactional
    @Override
    public void insertCoin(Coins coins) {
        coinRepository.insertCoin(coins);
    }

}
