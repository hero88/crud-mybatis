package com.allxone.coinmarket.service.impl;


import com.allxone.coinmarket.dto.response.CoinResponse;
import com.allxone.coinmarket.exception.auth.AuthenticateException;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.exception.core.ApplicationException;
import com.allxone.coinmarket.mapper.CoinsMapper;
import com.allxone.coinmarket.model.Coins;
import com.allxone.coinmarket.model.CoinsExample;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.CoinService;
import com.allxone.coinmarket.service.UserService;
import com.allxone.coinmarket.utilities.ValidatorUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoinsServiceImpl implements CoinService {
    private final CoinsMapper coinMapper;

    private final UserService userService;

    private final RestTemplate restTemplate;
    
    
    private final String apiUrl = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing";


    @Override
    public void addToCoin(Coins coins) throws ParamInvalidException {

        ValidatorUtils.checkNullParam(
                coins.getCoinmarketId()
                , coins.getQuantity());

        Users user = userService.getLoggedUser();

        coins.setUserId(user.getId());
        coins.setCreatedAt(new Date());

        coinMapper.insert(coins);
    }


    @Override
    public void updateCoin(Coins coins) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(coins.getId());

        Users user = userService.getLoggedUser();

        CoinsExample coinsExample = new CoinsExample();
        coinsExample.createCriteria().andIdEqualTo(coins.getId());

        Coins dbCoins = coinMapper.selectByPrimaryKey(coins.getId());
        dbCoins.setQuantity(coins.getQuantity());
        dbCoins.setUpdatedAt(new Date());

        if(dbCoins.getUserId()!=user.getId()){
            throw new AuthenticateException("You do not have permission to perform this action!");
        }

        coinMapper.updateByPrimaryKey(dbCoins);
    }


    @Override
    public List<Coins> findByUser(Long userId) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(userId);

        CoinsExample coinsExample = new CoinsExample();
        coinsExample.createCriteria().andUserIdEqualTo(userId);

        return coinMapper.selectByExample(coinsExample);
    }


    @Override
    public Coins findById(Long id) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(id);
        return coinMapper.selectByPrimaryKey(id);
    }


    @Override
    public void deleteById(Long id) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(id);

        Users user = userService.getLoggedUser();

        Coins dbCoins = coinMapper.selectByPrimaryKey(id);

        if(dbCoins.getUserId()!=user.getId()){
            throw new AuthenticateException("You do not have permission to perform this action!");
        }

        coinMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<Coins> findByLoggedUser() throws ParamInvalidException {
        Users user = userService.getLoggedUser();

        List<Coins> coinsList = findByUser(user.getId());

        coinsList.forEach(item -> {
            try {
                item.setPrice(getPriceCoinById(item.getCoinmarketId()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        return coinsList;

    }


    public Double getPriceCoinById(int id) throws Exception {
        try {
        String response = makeRequest(apiUrl);

   
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(response).getAsJsonObject();


        JsonArray cryptoCurrencyList = rootObject.getAsJsonObject("data").getAsJsonArray("cryptoCurrencyList");
        JsonObject targetCoin = findCoinById(cryptoCurrencyList, id);

      
        if (targetCoin != null) {
            double  price = targetCoin.getAsJsonArray("quotes").get(0).getAsJsonObject().get("price").getAsDouble();
            System.out.println("Price of the coin with ID " + id + ": " + price);
            return price;
        } else {
            throw new ApplicationException("Coin with ID " + id + " not found");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }


        return null;
    }



    private String makeRequest(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

       
        connection.setRequestMethod("GET");

    
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
           
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            throw new RuntimeException("Failed to retrieve data. Response Code: " + responseCode);
        }
    }


    private  JsonObject findCoinById(JsonArray cryptoCurrencyList, long coinId) {
        for (JsonElement coin : cryptoCurrencyList) {
            if (coin.getAsJsonObject().get("id").getAsLong() == coinId) {
                return coin.getAsJsonObject();
            }
        }
        return null;
    }

}
