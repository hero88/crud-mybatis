package com.allxone.coinmarket.service;

import com.allxone.coinmarket.model.Roles;
import com.allxone.coinmarket.model.enums.ERole;

public interface RoleService {

    Roles getRoleByName(ERole role);
}
