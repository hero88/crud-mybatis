package com.allxone.JavaMyBatis.config;

import com.allxone.JavaMyBatis.enums.AuthenticationType;
import com.allxone.JavaMyBatis.model.User;
import com.allxone.JavaMyBatis.service.CustomOAuth2UserService;
import com.allxone.JavaMyBatis.service.UserService;
import com.allxone.JavaMyBatis.service.impl.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    private final UserService userService;

    private final CustomOAuth2UserService customOAuth2UserService;

    @Value("${app.oauth2.return-url}")
    private String returnUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof OAuth2User oauth2User) {

                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                String clientName = oauthToken.getAuthorizedClientRegistrationId();
                AuthenticationType type = AuthenticationType.valueOf(clientName.toUpperCase());

                User user = customOAuth2UserService.getOrCreateUser(oauth2User, type);

                String accessToken = jwtService.generateAccessToken(user);

                String redirectUrl = returnUrl.formatted(accessToken);

                response.sendRedirect(redirectUrl);

            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication không thành công");
            }
        }

    }
}
