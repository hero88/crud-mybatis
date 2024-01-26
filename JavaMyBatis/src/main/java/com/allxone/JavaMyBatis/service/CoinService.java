package com.allxone.JavaMyBatis.service;

import com.allxone.JavaMyBatis.dto.response.CoinApiReponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CoinService {

    CoinApiReponse fetchApiDataCoins() throws IOException;
}
