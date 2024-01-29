package crudmybatis.services;

import crudmybatis.mappers.RoleMapper;
import crudmybatis.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public Role findById(long userId) {
        return roleMapper.selectRoleById(userId);
    }

    public Role findByName(String name) {
        return roleMapper.selectRoleByName(name);
    }
}
