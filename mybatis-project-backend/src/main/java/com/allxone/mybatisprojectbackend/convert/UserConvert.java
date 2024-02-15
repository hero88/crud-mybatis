package com.allxone.mybatisprojectbackend.convert;

import com.allxone.mybatisprojectbackend.dto.request.UserRequest;
import com.allxone.mybatisprojectbackend.dto.response.UserResponse;
import com.allxone.mybatisprojectbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {

    public static UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .gender(user.getGender())
                .address(user.getAddress())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User toUser(UserRequest UserRequest) {
        return User.builder()
                .id(UserRequest.getId())
                .firstname(UserRequest.getFirstname())
                .lastname(UserRequest.getLastname())
                .gender(UserRequest.getGender())
                .address(UserRequest.getAddress())
                .age(UserRequest.getAge())
                .email(UserRequest.getEmail())
                .phoneNumber(UserRequest.getPhoneNumber())
                .build();
    }
}
