package com.allxone.coinmarket.service;


import com.allxone.coinmarket.enums.AuthenticationType;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.oauth2.user.OAuth2UserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;


public interface CustomOAuth2UserService {
	
    Users createNewUser(OAuth2UserInfo oAuth2UserInfo, AuthenticationType type);

    Users getOrCreateUser(OAuth2User auth2User, AuthenticationType type);
}
