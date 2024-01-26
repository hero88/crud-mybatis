package com.allxone.mybatisprojectservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum ERole {
    ADMIN("ADMIN"), USER("USER");

    private final String value;

    ERole(String value) {
        this.value = value;
    }

    @JsonCreator
    public ERole parse(String value) {
        for (ERole type : values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("The value of the role is invalid");
    }
}