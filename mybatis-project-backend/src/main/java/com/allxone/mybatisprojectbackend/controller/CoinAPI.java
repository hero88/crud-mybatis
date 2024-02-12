package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.dto.AddCoinRequest;
import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.service.CoinService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/coins")
public class CoinAPI {

    @Autowired
    private CoinService coinService;

    @Value("${coinmarketcap.api.url}")
    private String coinMarketCapUrl;

    @GetMapping("/getCoinMarketData")
    public ResponseEntity<?> getCoinMarketData(@RequestParam(name = "start", required = false) Integer start,
                                               @RequestParam(name = "limit", required = false) Integer limit,
                                               @RequestParam(name = "sortBy", required = false) String sortBy,
                                               @RequestParam(name = "sortType", required = false) String sortType,
                                               @RequestParam(name = "convert", required = false) String convert,
                                               @RequestParam(name = "crytoType", required = false) String crytoType,
                                               @RequestParam(name = "tagType", required = false) String tagType,
                                               @RequestParam(name = "audited", required = false) String audited,
                                               @RequestParam(name = "aux", required = false) String aux) {
        HashMap<String, Object> result = new HashMap<>();


        try {
            if (start == null
                    && limit == null
                    && sortBy == null
                    && sortType == null
                    && convert == null
                    && crytoType == null
                    && tagType == null
                    && audited == null
                    && aux == null) {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> responseString = restTemplate.getForEntity(coinMarketCapUrl, String.class);

                ObjectMapper objectMapper = new ObjectMapper();
                Object data = objectMapper.readValue(responseString.getBody(), Object.class);

                result.put("success", true);
                result.put("message", "Success to call api from market");
                result.put("data", data);
            } else {
                String fullUrl = String.format("%s?start=%d&limit=%d&sortBy=%s&sortType=%s&convert=%s&crytoType=%s&tagType=%s&audited=%s&aux=%s",
                        coinMarketCapUrl, start, limit, sortBy, sortType, convert, crytoType, tagType, audited, aux);
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object> data = restTemplate.getForEntity(fullUrl, Object.class);

                result.put("success", true);
                result.put("message", "Success to call api from market");
                result.put("data", data);
            }


        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call api from market");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);

    }

    @GetMapping("/getAllCoins")
    public ResponseEntity<?> getAllCoins() {

        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            List<Coin> data = coinService.getAllCoins();
            result.put("success", true);
            result.put("message", "Call API getAllCoins successfully");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call API getAllCoins fail");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/addCoin")
    public ResponseEntity<?> doAddCoin(@RequestBody AddCoinRequest newCoin){
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            coinService.addCoin(newCoin);
            result.put("success", true);
            result.put("message", "Call API doAddCoin successfully");
            result.put("data", newCoin);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call API doAddCoin fail");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/updateCoin")
    public ResponseEntity<?> doUpdateCoin(@RequestParam("id") Long id, @RequestParam("quantity") int quantity){
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            coinService.updateCoin(id, quantity);
            result.put("success", true);
            result.put("message", "Call API doUpdateCoin successfully");
            result.put("data", id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call API doUpdateCoin fail");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteCoin")
    public ResponseEntity<?> doDeleteCoin(@RequestParam("id") Long id){
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            coinService.deleteCoin(id);
            result.put("success", true);
            result.put("message", "Call API doUpdateCoin successfully");
            result.put("data", id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call API doUpdateCoin fail");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }


}
