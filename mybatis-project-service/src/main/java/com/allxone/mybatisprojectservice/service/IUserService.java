package com.allxone.mybatisprojectservice.service;

import com.allxone.mybatisprojectservice.dto.user.UserDTO;
import com.allxone.mybatisprojectservice.model.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {

    Users findByUserId(Long userId);

    Users findByEmail(String email);

    List<UserDTO> findAllUserDTO();

    @Transactional
    void activeUser(Users user);

    @Transactional
    void updateUser(Users user);

    @Transactional
    void insertUser(Users user);
}
