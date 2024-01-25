package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Coin;
import com.example.demo.service.CoinService;

@RestController
@RequestMapping("v1/coin")
@CrossOrigin
public class CoinController {
	 @Autowired
	 private CoinService coinService;
	 
	 @GetMapping
	 @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	 public ResponseEntity<List<Coin>> findAll(Authentication auth) {
		 System.out.println(auth.getName());
	        return ResponseEntity.ok(coinService.findAll());
	 }

	 @GetMapping("/{id}")
	public ResponseEntity<Coin> findById(@PathVariable long id){
			return ResponseEntity.ok(coinService.findById(id));
	 }

	 @DeleteMapping("/delete")
	public void deleteAll() {
		coinService.deleteAll();
	}

	@PostMapping("/create")
	public void createCoin(@RequestBody Coin coin){
		 coinService.insert(new Coin(coin.getName(), coin.getSymbol(), coin.getCoinMarketId(), coin.getQuantity(), 1));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable long id){
		 coinService.deleteById(id);
	}


}
