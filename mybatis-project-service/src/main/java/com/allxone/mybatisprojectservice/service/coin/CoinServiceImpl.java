package com.allxone.mybatisprojectservice.service.coin;

import com.allxone.mybatisprojectservice.dto.coin.CoinDTO;
import com.allxone.mybatisprojectservice.model.Coins;
import com.allxone.mybatisprojectservice.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinServiceImpl implements ICoinService{
    @Autowired
    private CoinRepository coinRepository;

    @Override
    public Coins findById(Long id) {
        return coinRepository.findById(id);
    }

    @Override
    public List<CoinDTO> findByUserId(Long userId) {
        List<Coins> coins = coinRepository.findByUserId(userId);
        return coins.stream()
                .map(Coins::toCoinDTO)
                .collect(Collectors.toList());
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
