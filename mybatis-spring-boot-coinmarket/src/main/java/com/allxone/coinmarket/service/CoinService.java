package com.allxone.coinmarket.service;

import com.allxone.coinmarket.dto.response.CoinApiReponse;
import com.allxone.coinmarket.dto.response.CoinsUserReponse;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.Coins;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CoinService {

    CoinApiReponse fetchApiDataCoins(Integer sl) throws IOException;
    List<CoinsUserReponse> getAllCoinsUser() throws IOException;
    void addToCoin(Coins coins) throws ParamInvalidException;
    void updateCoin(Coins coins) throws ParamInvalidException;
    List<Coins> findByUser(Long userId) throws ParamInvalidException;
    Coins findById(Long id) throws ParamInvalidException;

    void deleteById(Long id) throws ParamInvalidException;

    List<Coins> findByLoggedUser() throws ParamInvalidException;

    Map<String, Object> getHistoryPriceCoin(Integer coinId);
}
