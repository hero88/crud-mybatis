package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CoinMapper;
import com.example.demo.model.Coin;

@Service
public class CoinService {

	@Autowired
	private CoinMapper coinMapper;

	public List<Coin> findAll() {
        return coinMapper.findAll();
    }

    public Coin findById(long id) {
        return coinMapper.findById(id);
    }

    public void deleteAll(){
       coinMapper.deleteAll();
    }

    public void insert(Coin coin){
        coinMapper.insert(coin);
    }

    public void deleteById(long id){
        coinMapper.deleteById(id);
    }
}
