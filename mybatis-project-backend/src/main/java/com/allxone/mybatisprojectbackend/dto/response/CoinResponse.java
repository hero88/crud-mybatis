package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CoinResponse {
    private Long id;
    private Long userId;
    private String name;
    private String symbol;
    private String coinMarketId;
    private Long quantity;
    private Instant createdAt;
    private Instant updatedAt;
}
