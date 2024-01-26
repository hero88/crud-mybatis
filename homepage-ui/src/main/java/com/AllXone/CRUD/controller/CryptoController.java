package com.AllXone.CRUD.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CryptoController {

    @Value("${coinmarketcap.api.url}")
    private String coinMarketCapApiUrl;
    @GetMapping("/coinmarketcap")
    public ResponseEntity<String> getCoinMartketCapData(@RequestParam(name = "start") int start,
                                                        @RequestParam(name = "limit") int limit,
                                                        @RequestParam(name = "sortBy") String sortBy,
                                                        @RequestParam(name = "sortType") String sortType,
                                                        @RequestParam(name = "convert") String convert,
                                                        @RequestParam(name = "crytoType") String crytoType,
                                                        @RequestParam(name = "tagType") String tagType,
                                                        @RequestParam(name = "audited") String audited,
                                                        @RequestParam(name = "aux") String aux
                                                        ){
        String fullUrl = String.format("%s?start=%d&limit=%d&sortBy=%s&sortType=%s&convert=%s&crytoType=%s&tagType=%s&audited=%s&aux=%s",
                coinMarketCapApiUrl, start, limit, sortBy, sortType, convert,crytoType,tagType,audited,aux);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(fullUrl, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}
