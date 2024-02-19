package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CoinChartDataResponse {
    private List<CoinChartData> data;


    @Data
    @Builder
    public static class CoinChartData {
        private String timestamp;
        private Double value;
    }
}
