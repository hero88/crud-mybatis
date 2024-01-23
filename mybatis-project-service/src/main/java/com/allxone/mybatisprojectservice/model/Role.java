package com.allxone.mybatisprojectservice.model;

import com.allxone.mybatisprojectservice.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Role {

    private Long userId;

    private String role;

    private ERole name;

    private List<Users> users;

    public Role(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Role(Long userId) {
        this.userId = userId;
    }

    public RoleDTO toRoleDTO() {
        return new RoleDTO()
                .setUserId(userId)
                .setRole(role);
    }
}
