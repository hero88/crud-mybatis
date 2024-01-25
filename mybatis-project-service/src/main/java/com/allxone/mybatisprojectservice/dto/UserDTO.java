package com.allxone.mybatisprojectservice.dto;

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

    private String firstName;

    private String lastName;

    private Boolean isActive;

    private String email;

    private String phoneNumber;
}
