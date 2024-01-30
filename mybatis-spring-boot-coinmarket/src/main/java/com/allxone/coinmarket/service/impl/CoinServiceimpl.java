package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.auth.UserDetail.CustomUserDetail;
import com.allxone.coinmarket.auth.jwt.JWTProvider;
import com.allxone.coinmarket.dto.response.CoinApiReponse;
import com.allxone.coinmarket.dto.response.CoinsUserReponse;
import com.allxone.coinmarket.dto.response.dataApi.CryptoCurrency;
import com.allxone.coinmarket.mapper.CoinsMapper;
import com.allxone.coinmarket.model.Coins;
import com.allxone.coinmarket.service.CoinService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinServiceimpl implements CoinService {
    private final CoinsMapper coinsMapper;
    private final JWTProvider jwtProvider;
    @Override
    public CoinApiReponse fetchApiDataCoins(Integer sl) throws IOException {
        String apiUrl = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit="+sl+"&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false&aux=ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d,self_reported_circulating_supply,self_reported_market_cap";
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

    @Override
    public List<CoinsUserReponse> getAllCoinsUser() throws IOException {
        CustomUserDetail user = jwtProvider.getCurrentAuthenticatedAccount();
        System.out.println(user.getId());
        List<Coins> listCoins = coinsMapper.selectAllCoinsByUserId(user.getId());
        List<CoinsUserReponse> userCoinsReponseList = new ArrayList<>();
        CoinApiReponse coinApiReponseList = fetchApiDataCoins(100);
        for (Coins coin : listCoins){
            CoinsUserReponse userReponse = new CoinsUserReponse();
            List<CryptoCurrency> list = coinApiReponseList.getData().getCryptoCurrencyList().stream().filter(c -> c.getId() == coin.getCoinmarketId()).collect(Collectors.toList());
            userReponse.setId(coin.getCoinmarketId());
            userReponse.setSymbol(coin.getSymbol());
            userReponse.setPrice(list.get(0).getQuotes().get(0).getPrice());
            userReponse.setName(coin.getName());
            userReponse.setQuantity(coin.getQuantity());
            userReponse.setAmount(coin.getQuantity() * list.get(0).getQuotes().get(0).getPrice());
            userCoinsReponseList.add(userReponse);
        }
        System.out.println(userCoinsReponseList.size());
        return userCoinsReponseList;
    }
}
