package crudmybatis.services;

import crudmybatis.mappers.CoinsMapper;
import crudmybatis.mappers.UserMapper;
import crudmybatis.models.Coin;
import crudmybatis.models.User;
import crudmybatis.payload.request.CoinRequest;
import crudmybatis.payload.request.UpdateCoinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CoinService {
    @Autowired
    private CoinsMapper coinsMapper;

    public int insertCoins(Coin coin){
        return coinsMapper.insertCoins(coin);
    }

    public int deleteCoinById(Long id){
        return coinsMapper.deleteCoinById(id);
    }

    public int deleteCoinByUserId(long userId){
        return coinsMapper.deleteCoinByUserId(userId);
    }

    public int updateCoin(Coin coin){
        return coinsMapper.updateCoin(coin);
    }

    public List<Coin>getAllCoinsByUserId(Long userId){
        return coinsMapper.getAllCoinsByUserId(userId);
    }

    public Coin findById(long id) {
        return coinsMapper.selectCoinById(id);
    }

    public Coin findByUserIdAndCoinmarketId(long userId, int coinmarketId) {
        Coin coin = new Coin();
        coin.setUserId(userId);
        coin.setCoinmarketId(coinmarketId);
        return coinsMapper.selectCoinByUserIdAndCoinmarketId(coin);
    }
}
