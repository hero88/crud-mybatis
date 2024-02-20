package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

@Data
public class CryptoCurrencyRequest {
    private int start;
    private int limit;
    private String sortBy;
    private String sortType;
    private String convert;
    private String cryptoType;
    private String tagType;
    private String audited;
    private String aux;
}
