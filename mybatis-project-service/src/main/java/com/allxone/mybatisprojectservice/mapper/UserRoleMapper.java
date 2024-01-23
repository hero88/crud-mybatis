package com.allxone.mybatisprojectservice.mapper;

import com.allxone.mybatisprojectservice.model.UserRole;
import org.apache.ibatis.annotations.Param;


public interface UserRoleMapper {

    UserRole findByUserId(@Param("userId") Long userId);

}
