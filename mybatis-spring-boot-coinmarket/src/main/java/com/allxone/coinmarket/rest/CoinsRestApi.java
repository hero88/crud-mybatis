package com.allxone.coinmarket.rest;


import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.Coins;
import com.allxone.coinmarket.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/coins")
public class CoinsRestApi {
    private final CoinService coinService;

    @PostMapping
    public ResponseEntity<?> addToCoin(@RequestBody Coins coins) throws ParamInvalidException {
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
}
