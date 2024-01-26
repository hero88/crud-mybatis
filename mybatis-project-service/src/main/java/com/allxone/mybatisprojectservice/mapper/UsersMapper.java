package com.allxone.mybatisprojectservice.mapper;

import com.allxone.mybatisprojectservice.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UsersMapper {

    Users findByEmail(String email);

    void insertUser(Users user);
    
    void updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);

}

