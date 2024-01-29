package crudmybatis.services;

import crudmybatis.mappers.UserRoleMapper;
import crudmybatis.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public int insertUserRole(UserRole userRole) {
        return userRoleMapper.insertUserRole(userRole);
    }

    public int deleteUserRoleByUserId(long id) {
        return userRoleMapper.deleteUserRoleByUserId(id);
    }
}
