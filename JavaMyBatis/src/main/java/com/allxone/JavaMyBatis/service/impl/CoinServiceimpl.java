package com.allxone.JavaMyBatis.service.impl;

import com.allxone.JavaMyBatis.dto.response.CoinApiReponse;
import com.allxone.JavaMyBatis.service.CoinService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class CoinServiceimpl implements CoinService {
    private final String apiUrl = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=100&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false&aux=ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d,self_reported_circulating_supply,self_reported_market_cap";
    @Override
    public CoinApiReponse fetchApiDataCoins() throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Thiết lập phương thức GET để lấy dữ liệu
        connection.setRequestMethod("GET");

        // Đọc dữ liệu từ InputStream của kết nối
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            Gson gson = new Gson();
            CoinApiReponse coinApiResponse = gson.fromJson(response.toString(), CoinApiReponse.class);
            return coinApiResponse;
        } finally {
            // Đóng kết nối sau khi sử dụng
            connection.disconnect();
        }
    }
}
