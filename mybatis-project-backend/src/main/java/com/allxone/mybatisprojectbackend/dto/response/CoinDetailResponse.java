package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
public class CoinDetailResponse {
    private CoinDetail data;

    @Data
    public static class CoinDetail {
        private Map<Long, Point> points;
        private Status status;
    }

    @Data
    public static class Point {
        private List<Double> v;
        private List<Double> c;
    }

    @Data
    public static class Status {
        private Instant timestamp;
        private String error_code;
        private String error_message;
        private String elapsed;
        private int credit_count;
    }
}
