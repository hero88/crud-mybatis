package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coin")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class CoinController {

    private final CoinService coinService;

    @GetMapping("")
    public CommonResponse<List<Coin>> getAllCoins() {
        try {
            List<Coin> data = coinService.getAllCoins();
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    ;

    @PostMapping("")
    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<Coin> saveCoin(@RequestBody Coin coin) {

        try {
            Coin data = coinService.saveCoin(coin);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    ;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public CommonResponse<Long> deleteCoinById(@PathVariable Long id) {

        try {
            coinService.deleteCoinById(id);
            return CommonResponse.success(id);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    ;

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<Coin> updateCoin(@RequestBody Coin coin) {

        try {
            Coin data = coinService.updateCoin(coin);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }

    }

    ;
}
