package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

@Data
public class CoinRequest {
    private Long id;
    private Long userId;
    private String name;
    private String symbol;
    private String coinMarketId;
    private Long quantity;
}
