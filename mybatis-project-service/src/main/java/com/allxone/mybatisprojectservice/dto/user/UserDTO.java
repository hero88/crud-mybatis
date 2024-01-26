package com.allxone.mybatisprojectservice.dto.user;

import com.allxone.mybatisprojectservice.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    private String password;

    private String firstName;

    private String lastName;

    private String gender;

    private String address;

    private Byte age;

    private Boolean isActive;

    private String email;

    private String phoneNumber;

    private Role role;
}
