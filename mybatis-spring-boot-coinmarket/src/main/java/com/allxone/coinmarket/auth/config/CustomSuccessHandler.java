package com.allxone.coinmarket.auth.config;

import com.allxone.coinmarket.enums.AuthenticationType;
import com.allxone.coinmarket.auth.UserDetail.CustomUserDetail;
import com.allxone.coinmarket.auth.jwt.JWTProvider;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.CustomOAuth2UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	JWTProvider jwtProvider;
	
	@Autowired
    CustomOAuth2UserService customOAuth2UserService;

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

                Users user = customOAuth2UserService.getOrCreateUser(oauth2User, type);

                String accessToken = jwtProvider.createToken(new CustomUserDetail(user,null));

                String redirectUrl = returnUrl.formatted(accessToken);

                response.sendRedirect(redirectUrl);

            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication không thành công");
            }
        }

    }
}
