package com.example.demo.service;

import com.example.demo.mapper.CoinMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }
}
