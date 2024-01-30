package com.allxone.coinmarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinResponse {

    private long id;
    private String name;
    private String symbol;

    private Quote quotes;
}
