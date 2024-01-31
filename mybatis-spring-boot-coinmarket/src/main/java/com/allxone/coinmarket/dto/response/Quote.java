package com.allxone.coinmarket.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {
    private String name;
    private double price;
    private double volume24h;
    private double volume7d;
    private double volume30d;
    private double marketCap;
    private double selfReportedMarketCap;
    private double percentChange1h;
    private double percentChange24h;
    private double percentChange7d;
    private String lastUpdated;
    private double percentChange30d;
    private double percentChange60d;
    private double percentChange90d;
    private double fullyDilluttedMarketCap;
    private double marketCapByTotalSupply;
    private double dominance;
    private double turnover;
    private double ytdPriceChangePercentage;
    private double percentChange1y;
}
