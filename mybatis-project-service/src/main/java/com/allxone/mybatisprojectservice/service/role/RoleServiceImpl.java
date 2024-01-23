package com.allxone.mybatisprojectservice.service.role;

import com.allxone.mybatisprojectservice.dto.RoleDTO;
import com.allxone.mybatisprojectservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> findAllRoleDTO() {
        return roleRepository.findAllRoleDTO();
    }
}
