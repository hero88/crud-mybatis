package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.ChangePasswordRequest;
import com.allxone.mybatisprojectbackend.model.Coin;
import com.allxone.mybatisprojectbackend.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    Optional<User> findByEmail(String email);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    User saveUser(User user);
    User updateUser(User user);
    User getUserById(Long id);
}
