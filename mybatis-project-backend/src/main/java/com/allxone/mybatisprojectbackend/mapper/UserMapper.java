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
    void updateUserPassword(User user);
    Optional<User> findByEmail(String email);
    Optional<User> loadUserByUsername(String email);
    User getUserById(Long id);
    void deleteUserById(Long id);
}
