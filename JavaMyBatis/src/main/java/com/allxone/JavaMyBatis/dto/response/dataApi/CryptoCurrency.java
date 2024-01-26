package com.allxone.JavaMyBatis.dto.response.dataApi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {

    private int id;
    private String name;
    private String symbol;
    private String slug;
    private int cmcRank;
    private int marketPairCount;
    private double circulatingSupply;
    private double selfReportedCirculatingSupply;
    private double totalSupply;
    private double maxSupply;
    private double ath;
    private double atl;
    private double high24h;
    private double low24h;
    private int isActive;
    private String lastUpdated;
    private String dateAdded;
    private List<Quote> quotes;
    private boolean isAudited;
    private List<Integer> badges;
}
