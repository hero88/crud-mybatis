package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    Optional<User> findByEmail(String email);
    User getUserById(Long id);
}
