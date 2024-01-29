package com.allxone.coinmarket.service;

import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.Coins;

import java.util.List;

public interface CoinService {
    void addToCoin(Coins coins) throws ParamInvalidException;

    void updateCoin(Coins coins) throws ParamInvalidException;

    List<Coins> findByUser(Long userId) throws ParamInvalidException;

    Coins findById(Long id) throws ParamInvalidException;

    void deleteById(Long id) throws ParamInvalidException;

    List<Coins> findByLoggedUser() throws ParamInvalidException;
}
