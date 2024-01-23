package com.allxone.mybatisprojectservice.model;


import com.allxone.mybatisprojectservice.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Users {

    private Long userId;

    private String userName;

    private String password;

    private String name;

    private String gender;

    private String address;

    private Integer age;

    private Boolean isActive;

    private String email;

    private String phoneNumber;


    public UserDTO toUserDTO() {
        return new  UserDTO()
                .setUserId(userId)
                .setUserName(userName)
                .setIsActive(isActive)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                ;
    }
}
