package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CryptoCurrency {

    private Integer id;
    private String name;
    private String symbol;
    private String slug;
    private Integer cmcRank;
    private Integer marketPairCount;
    private Double circulatingSupply;
    private Double selfReportedCirculatingSupply;
    private Double totalSupply;
    private Double maxSupply;
    private String ath;
    private Double atl;
    private Double high24h;
    private Double low24h;
    private Integer isActive;
    private Instant lastUpdated;
    private String dateAdded;
    private List<Quotes> quotes;
    private Boolean isAudited;
    private List<Integer> badges;
    @Data
    public static class Quotes {
        private String name;
        private Double price;
        private Double volume24h;
        private Double volume7d;
        private Double volume30d;
        private Double marketCap;
        private Double selfReportedMarketCap;
        private Double percentChange1h;
        private Double percentChange24h;
        private Double percentChange7d;
        private Instant lastUpdated;
        private Double percentChange30d;
        private Double percentChange60d;
        private Double percentChange90d;
        private Double fullyDilluttedMarketCap;
        private Double marketCapByTotalSupply;
        private Double dominance;
        private Double turnover;
        private Double ytdPriceChangePercentage;
        private Double percentChange1y;
    }
}
