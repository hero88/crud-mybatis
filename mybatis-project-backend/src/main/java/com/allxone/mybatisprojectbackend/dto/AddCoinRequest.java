package com.allxone.mybatisprojectbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCoinRequest {
    private String name;
    private String symbol;
    private int coinMarketId;
    private int quantity;
    private Long userId;
}
