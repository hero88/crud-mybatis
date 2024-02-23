package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.CoinRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinResponse;
import com.allxone.mybatisprojectbackend.model.Coin;
import org.springframework.stereotype.Component;

@Component
public class CoinConvert {
    public static CoinResponse toDto(Coin coin) {
        return CoinResponse.builder()
                .id(coin.getId()).userId(coin.getUserId())
                .name(coin.getName())
                .symbol(coin.getSymbol())
                .coinMarketId(coin.getCoinMarketId())
                .quantity(coin.getQuantity())
                .createdAt(coin.getCreatedAt())
                .updatedAt(coin.getUpdatedAt())
                .build();
    }

    public static Coin toCoin(CoinRequest coinRequest) {
        return Coin.builder().userId(coinRequest.getUserId())
                .id(coinRequest.getId())
                .name(coinRequest.getName())
                .symbol(coinRequest.getSymbol())
                .coinMarketId(coinRequest.getCoinMarketId())
                .quantity(coinRequest.getQuantity())
                .build();
    }
}
