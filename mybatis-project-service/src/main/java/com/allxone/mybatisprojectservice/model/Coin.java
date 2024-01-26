package com.allxone.mybatisprojectservice.model;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
    private Long id;
    private String name;
    private String symbol;
    private Integer coinmarketId;
    private Integer quantity;
    private Date createdAt;
    private Date updatedAt;
    private Long userId;
}
