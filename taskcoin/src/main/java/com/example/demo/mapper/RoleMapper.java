package com.example.demo.mapper;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface RoleMapper {
    Role findByName(String name);

    Set<Role> findByUsername(String username);
}
