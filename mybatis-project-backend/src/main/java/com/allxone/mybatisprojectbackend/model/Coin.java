package com.allxone.mybatisprojectbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
    private Long id;
    private String name;
    private String symbol;
    private int coinMarketId;
    private int quantity;
    private Long userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
