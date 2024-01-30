package com.allxone.coinmarket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String username;
    private String name;
    private String phoneNumber;
    private String gender;
    private int age;
    private String address;


}
