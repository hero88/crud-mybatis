package com.allxone.mybatisprojectbackend.enumaration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    USER_READ("admin:read"),
    USER_UPDATE("admin:update"),
    USER_CREATE("admin:create"),
    USER_DELETE("admin:delete"),
    ;

    @Getter
    private final String permission;
}
