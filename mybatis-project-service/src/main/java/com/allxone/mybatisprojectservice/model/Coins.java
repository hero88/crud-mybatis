package com.allxone.mybatisprojectservice.model;

import com.allxone.mybatisprojectservice.dto.coin.CoinDTO;
import com.allxone.mybatisprojectservice.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Coins {

    private Long id;

    private String name;

    private String symbol;

    private Long coinMarketId;

    private Long quantity;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;

    public CoinDTO toCoinDTO() {
        return new CoinDTO()
                .setId(id)
                .setName(name)
                .setSymbol(symbol)
                .setCoinMarketId(coinMarketId)
                .setQuantity(quantity)
                .setUserId(userId)
                ;
    }
}
