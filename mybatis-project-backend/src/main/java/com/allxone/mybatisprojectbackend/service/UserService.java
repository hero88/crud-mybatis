package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void updateUser(User user);

    public void deleteUser(Long id);

}
