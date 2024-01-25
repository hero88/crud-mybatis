package com.allxone.JavaMyBatis.service;

import com.allxone.JavaMyBatis.enums.AuthenticationType;
import com.allxone.JavaMyBatis.model.User;
import com.allxone.JavaMyBatis.oauth2.user.OAuth2UserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface CustomOAuth2UserService {
    User createNewUser(OAuth2UserInfo oAuth2UserInfo,AuthenticationType type);

    User getOrCreateUser(OAuth2User auth2User, AuthenticationType type);
}
