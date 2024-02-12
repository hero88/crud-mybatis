package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getAllUsers();

    public void updateUser(User user);

    public void deleteUser(@Param("id") Long id);

}
