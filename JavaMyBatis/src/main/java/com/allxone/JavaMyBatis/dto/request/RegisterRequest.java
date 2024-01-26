package com.allxone.JavaMyBatis.dto.request;

import com.allxone.JavaMyBatis.model.Role;
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

    private String firstName;

    private String lastName;

    private String gender;

    private String address;

    private Byte age;

    private String email;

    private String phoneNumber;

    private Role role;
}
