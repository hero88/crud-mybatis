package com.allxone.coinmarket.service.impl;


import com.allxone.coinmarket.exception.auth.AuthenticateException;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.mapper.CoinsMapper;
import com.allxone.coinmarket.model.Coins;
import com.allxone.coinmarket.model.CoinsExample;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.CoinService;
import com.allxone.coinmarket.service.UserService;
import com.allxone.coinmarket.utilities.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinsServiceImpl implements CoinService {
    private final CoinsMapper coinMapper;

    private final UserService userService;


    @Override
    public void addToCoin(Coins coins) throws ParamInvalidException {

        ValidatorUtils.checkNullParam(
                coins.getCoinmarketId()
                , coins.getQuantity());

        Users user = userService.getLoggedUser();

        coins.setUserId(user.getId());

        coinMapper.insert(coins);
    }


    @Override
    public void updateCoin(Coins coins) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(coins.getId());

        Users user = userService.getLoggedUser();

        CoinsExample coinsExample = new CoinsExample();
        coinsExample.createCriteria().andIdEqualTo(coins.getId());

        Coins dbCoins = coinMapper.selectByPrimaryKey(coins.getId());
        dbCoins.setQuantity(coins.getQuantity());

        if(dbCoins.getUserId()!=user.getId()){
            throw new AuthenticateException("");
        }

        coinMapper.updateByPrimaryKey(dbCoins);
    }


    @Override
    public List<Coins> findByUser(Long userId) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(userId);

        CoinsExample coinsExample = new CoinsExample();
        coinsExample.createCriteria().andUserIdEqualTo(userId);

        return coinMapper.selectByExample(coinsExample);
    }


    @Override
    public Coins findById(Long id) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(id);
        return coinMapper.selectByPrimaryKey(id);
    }


    @Override
    public void deleteById(Long id) throws ParamInvalidException {
        ValidatorUtils.checkNullParam(id);
        coinMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<Coins> findByLoggedUser() throws ParamInvalidException {
        Users user = userService.getLoggedUser();

        return findByUser(user.getId());
    }



}
