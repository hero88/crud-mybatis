package com.allxone.mybatisprojectservice.service.role;

import com.allxone.mybatisprojectservice.dto.RoleDTO;

import java.util.List;

public interface IRoleService {
    List<RoleDTO> findAllRoleDTO();
}
