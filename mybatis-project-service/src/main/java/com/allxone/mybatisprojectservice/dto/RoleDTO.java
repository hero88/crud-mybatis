package com.allxone.mybatisprojectservice.dto;

import com.allxone.mybatisprojectservice.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class RoleDTO {

    private Long userId;

    private String role;

    public RoleDTO (Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }
    public Role toRole() {
        return new Role()
                .setUserId(userId)
                .setRole(role);
    }

}
