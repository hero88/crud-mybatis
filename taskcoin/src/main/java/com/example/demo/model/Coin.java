package com.example.demo.model;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
	private long id;
	private String name;
	private String symbol;
	private int coinMarketId;
	private int quantity;
	private long userId;
	private Date createdAt;
	private Date updatedAt;
	public Coin(String name, String symbol, int coinMarketId, int quantity, long userId, Date createdAt,
			Date updatedAt) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.coinMarketId = coinMarketId;
		this.quantity = quantity;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Coin(String name, String symbol, int coinMarketId, int quantity, int userId) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.coinMarketId = coinMarketId;
		this.quantity = quantity;
		this.userId = userId;
	}
}
