package com.allxone.mybatisprojectservice.mapper;

import com.allxone.mybatisprojectservice.model.Role;
import org.apache.ibatis.annotations.Param;


public interface UserRoleMapper {

    Role findByUserId(@Param("userId") Long userId);

}
