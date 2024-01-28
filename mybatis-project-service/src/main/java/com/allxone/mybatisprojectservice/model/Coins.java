package com.allxone.mybatisprojectservice.model;

import com.allxone.mybatisprojectservice.dto.coin.CoinDTO;
import com.allxone.mybatisprojectservice.dto.user.UserDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Coins {

    private Long id;

    private Long coinId;

    private String name;

    private String symbol;

    private Long marketPairCount;

    private Long quantity;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;

    public CoinDTO toCoinDTO() {
        return new CoinDTO()
                .setId(id)
                .setCoinId(coinId)
                .setName(name)
                .setSymbol(symbol)
                .setMarketPairCount(marketPairCount)
                .setQuantity(quantity)
                .setUserId(userId)
                ;
    }
}
