package com.allxone.mybatisprojectbackend.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int addRole(long userId, long roleId);
}
