package com.allxone.coinmarket.rest;


import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.exception.core.ApplicationException;
import com.allxone.coinmarket.model.Coins;
import com.allxone.coinmarket.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/coins")
@CrossOrigin("*")
public class CoinsRestApi {
    private final CoinService coinService;

    @PostMapping
    public ResponseEntity<?> addToCoin(@RequestBody Coins coins) throws ApplicationException {
        coinService.addToCoin(coins);

        return ResponseEntity.ok("Successfully added coins to the account");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCoin(@PathVariable Long id,@RequestBody Coins coins)
            throws ParamInvalidException {
        coins.setId(id);
        coinService.updateCoin(coins);

        return ResponseEntity.ok("Coin update successfully!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCoin(@PathVariable Long id) throws ParamInvalidException {
        coinService.deleteById(id);

        return ResponseEntity.ok("Coin deleted successfully!");
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws ParamInvalidException {
        return ResponseEntity.ok(coinService.findById(id));
    }

    @GetMapping("find-by-user-logged")
    public ResponseEntity<?> findByUserLogged() throws ParamInvalidException {
        return ResponseEntity.ok(coinService.findByLoggedUser());
    }

    @GetMapping("home")
    public ResponseEntity<?> getAllCoins(@RequestParam(defaultValue = "100") Integer limit) throws IOException {
        return ResponseEntity.ok(ApiResponse.builder().message("ok").success(true).data(coinService.fetchApiDataCoins(limit)).build());
    }

    @GetMapping("myCoins")
    public ResponseEntity<?> getCoinsUser() throws IOException {
        return ResponseEntity.ok(ApiResponse.builder().data(coinService.getAllCoinsUser()).success(true).message("ok").build());
    }

    @GetMapping("history-coin")
	public ResponseEntity<?> getHistory(@RequestParam("id") Integer id) {
		Map<String, Object> history = coinService.getHistoryPriceCoin(id);
		if(history!=null) {
			return ResponseEntity.ok(history);
		}else {
			return ResponseEntity.badRequest().body(ApiResponse.builder().message("Get data failure").success(false).data(null));
		}
	}
}
