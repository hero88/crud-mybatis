package com.allxone.mybatisprojectservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Coins {

    private Long id;

    private String name;

    private String symbol;

    private Long coinmarketId;
    
}
