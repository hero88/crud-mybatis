package crudmybatis.controllers;

import crudmybatis.models.Coin;
import crudmybatis.models.User;
import crudmybatis.payload.request.CoinRequest;
import crudmybatis.payload.request.UpdateCoinRequest;
import crudmybatis.payload.response.MessageResponse;
import crudmybatis.services.CoinService;
import crudmybatis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/coins")
public class CoinController {
    @Autowired
    private CoinService coinService;
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> insertCoin(Authentication auth, @RequestBody CoinRequest coinRequest) {
        try {
            User user = userService.findByUsername(auth.getName());
            if (user.isActive()) {
                Coin existingCoin = coinService.findByUserIdAndCoinmarketId(user.getId(), coinRequest.getCoinmarketId());
                if (existingCoin == null) {
                    Coin coin = new Coin();
                    coin.setName(coinRequest.getName());
                    coin.setSymbol(coinRequest.getSymbol());
                    coin.setCoinmarketId(coinRequest.getCoinmarketId());
                    coin.setQuantity(coinRequest.getQuantity());
                    coin.setUserId(user.getId());
                    coin.setCreatedAt(new Date());
                    coinService.insertCoins(coin);
                    return ResponseEntity.ok().body(new MessageResponse("Insert successfully"));
                } else {
                    existingCoin.setQuantity(existingCoin.getQuantity() + coinRequest.getQuantity());
                    existingCoin.setUpdatedAt(new Date());
                    coinService.updateCoin(existingCoin);
                    return ResponseEntity.ok().body(new MessageResponse("Update successfully"));
                }
            } else {
                return new ResponseEntity<>(new MessageResponse("This user has not been activated yet or not is existed"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> deleteCoin(Authentication auth, @PathVariable long id) {
        try {
            User user = userService.findByUsername(auth.getName());
            Coin coin = coinService.findById(id);
            if (coin == null) {
                return ResponseEntity.notFound().build();
            }
            if (user.isActive() && Objects.equals(user.getId(), coin.getUserId())) {
                coinService.deleteCoinById(id);
                return new ResponseEntity<>(new MessageResponse("Delete Completely"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("This user has not been activated yet or not is existed", HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> updateCoin(Authentication auth, @PathVariable long id, @RequestBody UpdateCoinRequest updateCoinRequest) {
        try {
            User user = userService.findByUsername(auth.getName());
            Coin coin = coinService.findById(id);
            if (coin == null) {
                return ResponseEntity.notFound().build();
            }
            if (user.isActive() && Objects.equals(user.getId(), coin.getUserId())) {
                coin.setQuantity(updateCoinRequest.getQuantity());
                coin.setUpdatedAt(new Date());
                coinService.updateCoin(coin);
                return new ResponseEntity<>(new MessageResponse("Update Completely"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("This user has not been activated yet or not is existed", HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getAllCoins(Authentication auth) {
        try {
            User user = userService.findByUsername(auth.getName());
            if (user.isActive()) {
                List<Coin> listCoins = coinService.getAllCoinsByUserId(user.getId());
                //    System.out.println(listCoins.size());
                return ResponseEntity.ok(listCoins);
            } else {
                return new ResponseEntity<>("This user has not been activated yet or not is existed", HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getCoinById(Authentication auth, @PathVariable long id) {
        try {
            Coin coin = coinService.findById(id);
            if (coin == null) {
                return ResponseEntity.notFound().build();
            }
            User user = userService.findByUsername(auth.getName());
            if (user.isActive() && Objects.equals(user.getId(), coin.getUserId())) {
                return ResponseEntity.ok(coin);
            } else {
                return new ResponseEntity<>("This user has not been activated yet or not is existed", HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }
}
