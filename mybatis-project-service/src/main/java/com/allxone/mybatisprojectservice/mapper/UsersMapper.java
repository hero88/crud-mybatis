package com.allxone.mybatisprojectservice.mapper;

import com.allxone.mybatisprojectservice.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UsersMapper {

    Users findByUserId(@Param("userId") Long userId);

    Users findByEmail(String email);

    void insertUser(Users user);
}
