package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/coin")
@RequiredArgsConstructor
public class CoinController {

    private final CoinService coinService;

    @GetMapping("")
    public ResponseEntity<?> getAllCoins(){

        HashMap<String, Object> result = new HashMap<String, Object>();

        try{
            List<Coin> data = coinService.getAllCoins();
            result.put("success", true);
            result.put("message", "");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    };

    @PostMapping("")
    public ResponseEntity<?> saveCoin(@RequestBody Coin coin){

        HashMap<String, Object> result = new HashMap<String, Object>();

        try{
            Coin data = coinService.saveCoin(coin);
            result.put("success", true);
            result.put("message", "Saved successfully");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Failed to save");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoinById(@PathVariable Long id){

        HashMap<String, Object> result = new HashMap<String, Object>();

        try{
            coinService.deleteCoinById(id);
            result.put("success", true);
            result.put("message", "Deleted successfully");
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Failed to delete");
        }

        return ResponseEntity.ok(result);
    };

    @PutMapping("")
    public ResponseEntity<?> updateCoin(@RequestBody Coin coin){

        HashMap<String, Object> result = new HashMap<String, Object>();
        Coin data = coinService.updateCoin(coin);
        try{

            result.put("success", true);
            result.put("message", "Updated successfully");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Failed to update");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    };
}
