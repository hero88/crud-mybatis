package com.example.demo.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.sample.model.User;
import com.example.demo.sample.mybatis.xml.dao.UserDao;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> selectAllUser(){
        return userDao.selectAllUser();
    }
}
