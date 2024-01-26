package com.allxone.JavaMyBatis.service.impl;


import com.allxone.JavaMyBatis.mapper.UserMapper;
import com.allxone.JavaMyBatis.model.User;
import com.allxone.JavaMyBatis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper usersMapper;

    @Override
    public User findByUserId(Long userId) {
        return usersMapper.findByUserId(userId);
    }

    @Override
    public User findByEmail(String email) {
        return usersMapper.findByEmail(email);
    }

    @Override
    public Long insertUser(User user) {
       return usersMapper.insertUser(user);
    }
}
