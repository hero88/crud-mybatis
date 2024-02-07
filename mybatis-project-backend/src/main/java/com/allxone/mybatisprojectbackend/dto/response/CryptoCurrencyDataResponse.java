package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CryptoCurrencyDataResponse {
    private CryptoCurrencyData data;

    @Data
    public static class CryptoCurrencyData {
        private List<CryptoCurrency> cryptoCurrencyList;
    }
}
