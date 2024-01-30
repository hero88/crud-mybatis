package com.allxone.coinmarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoinsUserReponse {
    private Integer id;
    private String name;
    private String symbol;
    private Double amount;
    private Double price;
    private Integer quantity;
}
