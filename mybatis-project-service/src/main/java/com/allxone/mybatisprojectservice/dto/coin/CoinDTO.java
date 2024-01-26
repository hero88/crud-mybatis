package com.allxone.mybatisprojectservice.dto.coin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CoinDTO {

    private Long id;

    private String name;

    private String symbol;

    private Long coinMarketId;

    private Long quantity;

    private Long userId;

}
