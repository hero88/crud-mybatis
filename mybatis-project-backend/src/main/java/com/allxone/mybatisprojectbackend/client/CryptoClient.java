package com.allxone.mybatisprojectbackend.client;

import com.allxone.mybatisprojectbackend.config.feign.FeignConfig;
import com.allxone.mybatisprojectbackend.dto.request.CryptoCurrencyRequest;
import com.allxone.mybatisprojectbackend.dto.response.CryptoCurrencyDataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "cryptoClient",
        url = "${coinmarketcap-data.api.url}",
        configuration = FeignConfig.class
)
public interface CryptoClient {
    @GetMapping("/listing")
    CryptoCurrencyDataResponse getCoinMarketCapData(@SpringQueryMap CryptoCurrencyRequest request);
}
