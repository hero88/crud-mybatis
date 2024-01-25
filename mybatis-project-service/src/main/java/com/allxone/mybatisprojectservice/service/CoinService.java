package com.allxone.mybatisprojectservice.service;

import com.allxone.mybatisprojectservice.model.dto.response.CoinApiReponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CoinService {

    CoinApiReponse fetchApiDataCoins() throws IOException;
}
