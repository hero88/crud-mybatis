package com.allxone.JavaMyBatis.mapper;

import com.allxone.JavaMyBatis.enums.AuthenticationType;
import com.allxone.JavaMyBatis.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    User findByUserId(Long id);

    User findByEmail(String email);

    Long insertUser(User user);

    User findByEmailAndType(String email, AuthenticationType type);

}
