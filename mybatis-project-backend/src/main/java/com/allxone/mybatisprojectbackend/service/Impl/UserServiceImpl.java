package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.mapper.CoinMapper;
import com.allxone.mybatisprojectbackend.mapper.UserMapper;
import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CoinMapper coinMapper;

    @Override
    public List<User> getAllUsers() {

        return userMapper.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {

        List<Coin> coinList = coinMapper.getAllCoins();

        for (Coin coin : coinList) {
            if (coin.getUserId().equals(id)) {
                coinMapper.deleteCoin(coin.getId());
            }
        }
        userMapper.deleteUser(id);
    }
}
