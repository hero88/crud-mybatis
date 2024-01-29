package crudmybatis.mappers;

import crudmybatis.models.Coin;
import crudmybatis.models.User;
import crudmybatis.payload.request.CoinRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoinsMapper {
    int insertCoins(Coin coin);
    int deleteCoinById(Long coinId);
    int deleteCoinByUserId(long userId);
    int updateCoin(Coin coin);
    List<Coin> getAllCoinsByUserId(Long userId);
    Coin selectCoinById(long id);
    Coin selectCoinByUserIdAndCoinmarketId(Coin coin);
}
