package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.client.CryptoClient;
import com.allxone.mybatisprojectbackend.dto.request.CoinDetailRequest;
import com.allxone.mybatisprojectbackend.dto.request.CryptoCurrencyRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinChartDataResponse;
import com.allxone.mybatisprojectbackend.dto.response.CoinDetailResponse;
import com.allxone.mybatisprojectbackend.dto.response.CryptoCurrencyDataResponse;
import com.allxone.mybatisprojectbackend.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final CryptoClient cryptoClient;
    @Override
    public CryptoCurrencyDataResponse getCoinMarketCapData(CryptoCurrencyRequest request) {
        return cryptoClient.getCoinMarketCapData(request);
    }

    @Override
    public CoinChartDataResponse getCoinMarketCapDetailData(CoinDetailRequest request) {
        Map<Long, CoinDetailResponse.Point> points  = cryptoClient
                .getCoinMarketCapDetailData(request.getId(),request.getRange())
                .getData().getPoints();
        List<CoinChartDataResponse.CoinChartData> coinChartDataList = new ArrayList<>();
        points.forEach((timestampLong,point) -> {
            List<Double> values = point.getC();
            if (values != null && !values.isEmpty()) {
                Double value = values.get(0);
                String timestamp = String.valueOf(timestampLong);

                CoinChartDataResponse.CoinChartData coinChartData =
                        CoinChartDataResponse.CoinChartData
                                .builder()
                                .timestamp(timestamp)
                                .value(value)
                                .build();

                coinChartDataList.add(coinChartData);
            }
        });
        return CoinChartDataResponse.builder().data(coinChartDataList).build();
    }
}
