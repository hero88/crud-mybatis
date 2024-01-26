package com.allxone.JavaMyBatis.service;

import com.allxone.JavaMyBatis.model.User;

public interface UserService {
    User findByUserId(Long userId);

    User findByEmail(String email);

    Long insertUser(User user);
}
