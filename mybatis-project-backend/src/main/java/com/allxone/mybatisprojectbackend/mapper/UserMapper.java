package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getAllUsers();
}
