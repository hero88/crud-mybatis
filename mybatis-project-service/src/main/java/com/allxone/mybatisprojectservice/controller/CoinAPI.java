package com.allxone.mybatisprojectservice.controller;

import com.allxone.mybatisprojectservice.dto.coin.CoinDTO;
import com.allxone.mybatisprojectservice.model.Coins;
import com.allxone.mybatisprojectservice.service.ICoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coin")
public class CoinAPI {
    private final ICoinService coinService;

    @Autowired
    public CoinAPI(ICoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable Long userId) {
        List<CoinDTO> coinDTOS = coinService.findByUserId(userId);

        if(coinDTOS.isEmpty()) {
            return new ResponseEntity<>("No coin found.", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(coinDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Coins coins = coinService.findById(id);

            return new ResponseEntity<>(coins.toCoinDTO(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoin(@PathVariable Long id, @RequestBody Coins coin) {
        try {
            coin.setId(id);
            coinService.updateCoin(coin);
            return new ResponseEntity<>("Quantity of Coin updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update quantity of Coin.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> insertCoin(@RequestBody Coins coin) {
        try {
            coinService.insertCoin(coin);
            return new ResponseEntity<>("Successfully added coins to the account.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add coins to the account.", HttpStatus.BAD_REQUEST);
        }
    }
}
