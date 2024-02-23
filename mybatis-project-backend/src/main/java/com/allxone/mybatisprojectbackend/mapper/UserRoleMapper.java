package com.allxone.mybatisprojectbackend.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int addRole(Long userId, int roleId);
    void deleteUserRoleByUsUserId(Long userId);
}
