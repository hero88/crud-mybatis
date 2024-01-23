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

    private Long userId;

    private String userName;

    private Boolean isActive;

    private String email;

    private String phoneNumber;
}
