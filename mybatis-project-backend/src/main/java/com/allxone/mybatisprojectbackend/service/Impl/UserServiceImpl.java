package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.mapper.UserMapper;
import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        List<User> result = userMapper.getAllUsers();

        return result;
    }
}
