package com.allxone.mybatisprojectbackend.enumaration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.allxone.mybatisprojectbackend.enumaration.Permission.ADMIN_READ;
import static com.allxone.mybatisprojectbackend.enumaration.Permission.ADMIN_UPDATE;
import static com.allxone.mybatisprojectbackend.enumaration.Permission.ADMIN_DELETE;
import static com.allxone.mybatisprojectbackend.enumaration.Permission.ADMIN_CREATE;
@RequiredArgsConstructor
public enum Role {
    USER(Set.of(ADMIN_READ)),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE
            )
    )

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority>  authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
