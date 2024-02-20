package com.allxone.coinmarket.service.impl;


import com.allxone.coinmarket.auth.UserDetail.CustomUserDetail;
import com.allxone.coinmarket.auth.jwt.JWTProvider;
import com.allxone.coinmarket.dto.response.CoinApiReponse;
import com.allxone.coinmarket.dto.response.CoinsUserReponse;
import com.allxone.coinmarket.dto.response.CryptoCurrency;
import com.allxone.coinmarket.exception.auth.AuthenticateException;
import com.allxone.coinmarket.exception.common.DuplicateDataException;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.exception.core.ApplicationException;
import com.allxone.coinmarket.mapper.CoinsMapper;
import com.allxone.coinmarket.model.Coins;
import com.allxone.coinmarket.model.CoinsExample;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.CoinService;
import com.allxone.coinmarket.service.UserService;
import com.allxone.coinmarket.utilities.TimeUtils;
import com.allxone.coinmarket.utilities.ValidatorUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.*;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinsServiceImpl implements CoinService {
    private final CoinsMapper coinMapper;
    private final JWTProvider jwtProvider;
    private final UserService userService;
    private final RestTemplate restTemplate;
    private final TimeUtils timeUntil;

    private final String URI_HISTORY_PRICECOIN = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/detail/chart";
    private final String apiUrl = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing";


    @Override
    public CoinApiReponse fetchApiDataCoins(Integer sl) throws IOException {
        String apiUrl = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=1&limit=" + sl + "&sortBy=market_cap&sortType=desc&convert=USD&cryptoType=all&tagType=all&audited=false&aux=ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d,self_reported_circulating_supply,self_reported_market_cap";
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
        List<Coins> listCoins = coinMapper.selectAllCoinsByUserId(user.getId());
        List<CoinsUserReponse> userCoinsReponseList = new ArrayList<>();
        CoinApiReponse coinApiReponseList = fetchApiDataCoins(100);
        listCoins.forEach(coin -> {
            List<CryptoCurrency> cryptoCurrencyList = coinApiReponseList.getData().getCryptoCurrencyList().stream()
                    .filter(c -> c.getId() == coin.getCoinmarketId())
                    .collect(Collectors.toList());
            if (!cryptoCurrencyList.isEmpty()) {
                CryptoCurrency cryptoCurrency = cryptoCurrencyList.get(0);
                CoinsUserReponse userReponse = new CoinsUserReponse();
                userReponse.setId(coin.getCoinmarketId());
                userReponse.setSymbol(coin.getSymbol());
                userReponse.setPrice(cryptoCurrency.getQuotes().get(0).getPrice());
                userReponse.setName(coin.getName());
                userReponse.setQuantity(coin.getQuantity());
                userReponse.setAmount(coin.getQuantity() * cryptoCurrency.getQuotes().get(0).getPrice());

                userCoinsReponseList.add(userReponse);
            }
        });
        return userCoinsReponseList;
    }

    @Override
    public void addToCoin(Coins coins) throws ApplicationException {

        ValidatorUtils.checkNullParam(
                coins.getCoinmarketId()
                , coins.getQuantity());

        Users user = userService.getLoggedUser();

        CoinsExample coinsExample = new CoinsExample();
        coinsExample.createCriteria().andCoinmarketIdEqualTo(coins.getCoinmarketId()).andUserIdEqualTo(user.getId());

        List<Coins> coinsList = coinMapper.selectByExample(coinsExample);

        if (coinsList != null && !coinsList.isEmpty()) {
//            Coins dbCoin = coinsList.get(0);
            throw new DuplicateDataException("This coin already exists in the account");
        }

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

        if (dbCoins.getUserId() != user.getId()) {
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

        if (dbCoins.getUserId() != user.getId()) {
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

    @Override
    public Map<String, Object> getHistoryPriceCoin(Integer coinId) {
        RestTemplate restTemplate = new RestTemplate();
        String range = timeUntil.getListTimeStampEnd12MonthsAgo().get(0) + "~" + timeUntil.getListTimeStampEnd12MonthsAgo().get(timeUntil.getListTimeStampEnd12MonthsAgo().size() - 1);
        ResponseEntity<String> response = restTemplate
                .getForEntity(URI_HISTORY_PRICECOIN + "?id=" + coinId + "&range=" + range, String.class);
        try {
            if (response.getStatusCode().is2xxSuccessful()) {
                String responseData = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> dataMap;
                dataMap = objectMapper.readValue(responseData, Map.class);
                dataMap.put("timestamps", timeUntil.getListTimeStampEnd12MonthsAgo());
                dataMap.put("months", timeUntil.getEnglishMonthNamesLast12Months());
                return dataMap;
            } else {
                return null;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Double getPriceCoinById(int id) throws Exception {
        try {
            String response = makeRequest(apiUrl);


            JsonParser parser = new JsonParser();
            JsonObject rootObject = parser.parse(response).getAsJsonObject();


            JsonArray cryptoCurrencyList = rootObject.getAsJsonObject("data").getAsJsonArray("cryptoCurrencyList");
            JsonObject targetCoin = findCoinById(cryptoCurrencyList, id);


            if (targetCoin != null) {
                double price = targetCoin.getAsJsonArray("quotes").get(0).getAsJsonObject().get("price").getAsDouble();
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


    private JsonObject findCoinById(JsonArray cryptoCurrencyList, long coinId) {
        for (JsonElement coin : cryptoCurrencyList) {
            if (coin.getAsJsonObject().get("id").getAsLong() == coinId) {
                return coin.getAsJsonObject();
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> getAllCoin(int start, int limit, String sortBy, String sortType, String convert) {
        String apiUrl = "https://api.coinmarketcap.com/data-api/v3/cryptocurrency/listing?start=" + start + "&limit=" + limit + "&sortBy=" + sortBy + "&sortType=" + sortType + "&convert=" + convert + "&cryptoType=all&tagType=all&audited=false&aux=ath%2Catl%2Chigh24h%2Clow24h%2Cnum_market_pairs%2Ccmc_rank%2Cdate_added%2Cmax_supply%2Ccirculating_supply%2Ctotal_supply%2Cvolume_7d%2Cvolume_30d%2Cself_reported_circulating_supply%2Cself_reported_market_cap";
        String apiCoinDetail = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/info?id=";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        // Create HttpHeaders and set header X-CMC_PRO_API_KEY
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-CMC_PRO_API_KEY", "502fa2ee-6cd7-4ef6-8b67-2a7a6ace811e");

        // Create HttpEntity with headers
        HttpEntity<String> entityHeader = new HttpEntity<>(headers);
        try {
            if (response.getStatusCode().is2xxSuccessful()) {
                //read data from api top
                String responseData = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();

                //parse data to json
                JsonNode rootNode = objectMapper.readTree(responseData);
                JsonNode cryptoCurrencyListNode = rootNode.path("data").path("cryptoCurrencyList");

                for (JsonNode currency : cryptoCurrencyListNode) {
                    // sent request with information header
                    ResponseEntity<String> resp = restTemplate.exchange(apiCoinDetail+currency.path("id"), HttpMethod.GET, entityHeader, String.class);
                    if (resp.getStatusCode().is2xxSuccessful()) {
                        JsonNode rootNodeDetail = objectMapper.readTree(String.valueOf(resp.getBody()));
                        JsonNode detail = rootNodeDetail.path("data").path(currency.path("id").asText());
                        ((ObjectNode) currency).set("detail", detail);
                        System.out.println(currency.path("detail"));
                    }
                }

                Map<String, Object> dataMap;
                dataMap = objectMapper.readValue(rootNode.toString(), Map.class);
                return dataMap;
            } else {
                return null;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
