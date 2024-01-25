package com.allxone.mybatisprojectservice.model.dto.request;

import com.allxone.mybatisprojectservice.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String password;

    private String name;

    private String username;

    private String gender;

    private String address;

    private Byte age;

    private String email;

    private String phoneNumber;

    private Role role;
}
