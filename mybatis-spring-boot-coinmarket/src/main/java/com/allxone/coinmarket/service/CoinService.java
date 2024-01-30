package com.allxone.coinmarket.service;

import com.allxone.coinmarket.dto.response.CoinApiReponse;
import com.allxone.coinmarket.dto.response.CoinsUserReponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CoinService {

    CoinApiReponse fetchApiDataCoins(Integer sl) throws IOException;

    List<CoinsUserReponse> getAllCoinsUser() throws IOException;
}
