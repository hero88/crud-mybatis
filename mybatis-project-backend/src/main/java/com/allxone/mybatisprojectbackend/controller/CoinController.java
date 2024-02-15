package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.dto.request.CoinRequest;
import com.allxone.mybatisprojectbackend.dto.response.CoinResponse;
import com.allxone.mybatisprojectbackend.service.CoinService;
import lombok.RequiredArgsConstructor;
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
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class CoinController {

    private final CoinService coinService;

    @GetMapping("/getAllCoins")
    public CommonResponse<List<CoinResponse>> getAllCoins() {
        try {
            List<CoinResponse> data = coinService.getAllCoins();
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.print(e);
            return CommonResponse.error(null);
        }
    }

    @GetMapping("/getCoinById/{id}")
    public CommonResponse<CoinResponse> getCoinById(@PathVariable Long id) {
        try {
            CoinResponse data = coinService.getCoinById(id);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PostMapping("/saveCoin")
//    @PreAuthorize("hasAuthority('admin:create')")
    public CommonResponse<CoinResponse> saveCoin(@RequestBody CoinRequest coinRequest) {

        try {
            CoinResponse data = coinService.saveCoin(coinRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @DeleteMapping("/deleteCoinById/{id}")
//    @PreAuthorize("hasAuthority('admin:delete')")
    public CommonResponse<Long> deleteCoinById(@PathVariable Long id) {

        try {
            coinService.deleteCoinById(id);
            return CommonResponse.success(id);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updateCoin")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<CoinResponse> updateCoin(@RequestBody CoinRequest coinRequest) {

        try {
            CoinResponse data = coinService.updateCoin(coinRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }

    }
}
