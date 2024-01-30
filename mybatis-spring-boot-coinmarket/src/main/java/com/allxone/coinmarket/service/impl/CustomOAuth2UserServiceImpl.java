package com.allxone.coinmarket.service.impl;



import com.allxone.coinmarket.enums.AuthenticationType;
import com.allxone.coinmarket.mapper.UsersMapper;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.oauth2.user.FacebookOAuthUserInfo;
import com.allxone.coinmarket.oauth2.user.GoogleOAuth2UserInfo;
import com.allxone.coinmarket.oauth2.user.OAuth2UserInfo;
import com.allxone.coinmarket.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserServiceImpl implements CustomOAuth2UserService {
    private final UsersMapper userMapper;

@Override
    public Users createNewUser(OAuth2UserInfo oAuth2UserInfo, AuthenticationType type) {

        Users user = Users.builder()
                .id(null)
                .email(oAuth2UserInfo.getEmail())
                .username(oAuth2UserInfo.getEmail())
                .password(new BCryptPasswordEncoder().encode(RandomStringUtils.randomAlphanumeric(20)))
                .type(type)
                .active(true)
                .build();

        userMapper.insert(user);

        return userMapper.findByEmailAndType(oAuth2UserInfo.getEmail(), type);
    }


    @Override
    public Users getOrCreateUser(OAuth2User auth2User, AuthenticationType type) {

        OAuth2UserInfo oAuth2UserInfo = new GoogleOAuth2UserInfo(auth2User.getAttributes());

        if (type.equals(AuthenticationType.FACEBOOK)) {
            oAuth2UserInfo = new FacebookOAuthUserInfo(auth2User.getAttributes());
        }

        OAuth2UserInfo finalOAuth2UserInfo = oAuth2UserInfo;


        return Optional.ofNullable(userMapper.findByEmailAndType(finalOAuth2UserInfo.getEmail(), type))
                .orElseGet(() -> createNewUser(finalOAuth2UserInfo, type));

    }


}
