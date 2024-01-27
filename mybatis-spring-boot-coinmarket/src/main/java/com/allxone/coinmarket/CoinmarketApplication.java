package com.allxone.coinmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoinmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinmarketApplication.class, args);
	}

}
