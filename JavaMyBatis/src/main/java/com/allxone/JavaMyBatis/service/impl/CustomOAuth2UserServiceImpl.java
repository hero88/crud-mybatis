package com.allxone.JavaMyBatis.service.impl;


import com.allxone.JavaMyBatis.enums.AuthenticationType;
import com.allxone.JavaMyBatis.mapper.UserMapper;
import com.allxone.JavaMyBatis.model.Role;
import com.allxone.JavaMyBatis.model.User;
import com.allxone.JavaMyBatis.oauth2.user.FacebookOAuthUserInfo;
import com.allxone.JavaMyBatis.oauth2.user.GoogleOAuth2UserInfo;
import com.allxone.JavaMyBatis.oauth2.user.OAuth2UserInfo;
import com.allxone.JavaMyBatis.service.CustomOAuth2UserService;
import com.allxone.JavaMyBatis.service.UserService;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserServiceImpl implements CustomOAuth2UserService {
    private final UserMapper userMapper;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User createNewUser(OAuth2UserInfo oAuth2UserInfo, AuthenticationType type) {

        String[] nameParts = oAuth2UserInfo.getName().split("\\s", 2);

        User user = User.builder()
                .id(null)
                .email(oAuth2UserInfo.getEmail())
                .password(passwordEncoder.encode(RandomStringUtils.randomAlphanumeric(20)))
                .firstName(nameParts[0])
                .lastName(nameParts.length > 1 ? nameParts[1] : "")
                .role(Role.USER)
                .type(type)
                .isActive(true)
                .build();

        userMapper.insertUser(user);

        return userMapper.findByEmailAndType(oAuth2UserInfo.getEmail(), type);
    }

    @Override
    public User getOrCreateUser(OAuth2User auth2User, AuthenticationType type) {

        OAuth2UserInfo oAuth2UserInfo = new GoogleOAuth2UserInfo(auth2User.getAttributes());

        if (type.equals(AuthenticationType.FACEBOOK)) {
            oAuth2UserInfo = new FacebookOAuthUserInfo(auth2User.getAttributes());
        }

        OAuth2UserInfo finalOAuth2UserInfo = oAuth2UserInfo;


        return Optional.ofNullable(userMapper.findByEmailAndType(finalOAuth2UserInfo.getEmail(), type))
                .orElseGet(() -> createNewUser(finalOAuth2UserInfo, type));

    }


}
