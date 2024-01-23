package com.allxone.mybatisprojectservice.service.user;

import com.allxone.mybatisprojectservice.dto.UserDTO;
import com.allxone.mybatisprojectservice.model.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Users findByUserId(Long userId);

    Users getByUserName(String userName);

    List<UserDTO> findAllUserDTO();

    @Transactional
    void activeUser(Users user);

    @Transactional
    void updateUser(Users user);

    @Transactional
    void createUser(Users user);
}
