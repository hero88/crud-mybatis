package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.mapper.RolesMapper;
import com.allxone.coinmarket.model.Roles;
import com.allxone.coinmarket.model.RolesExample;
import com.allxone.coinmarket.model.enums.ERole;
import com.allxone.coinmarket.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RolesMapper rolesMapper;

    @Override
    public Roles getRoleByName(ERole role) {
        RolesExample rolesSql = new RolesExample();
        rolesSql.createCriteria().andNameEqualTo(role.name());
        return rolesMapper.selectByExample(rolesSql).get(0);
    }
}
