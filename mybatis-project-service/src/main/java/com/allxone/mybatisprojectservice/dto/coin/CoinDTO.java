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

    private Long coinId;

    private String name;

    private String symbol;

    private Long marketPairCount;

    private Long quantity;

    private Long userId;

}
