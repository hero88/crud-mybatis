package com.allxone.mybatisprojectservice.model;

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

    private Long user_id;

    private Date createdAt;

    private Date updatedAt;
    
}
