package com.allxone.mybatisprojectbackend.service;

import com.allxone.mybatisprojectbackend.dto.request.ChangePasswordRequest;
import com.allxone.mybatisprojectbackend.dto.request.UserRequest;
import com.allxone.mybatisprojectbackend.dto.response.UserResponse;
import com.allxone.mybatisprojectbackend.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> getAllUsers();
    Optional<User> findByEmail(String email);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    UserResponse saveUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest);
    User getUserById(Long id);
    void activatedUser(User user);
    void deleteUserById(Long id);
}
