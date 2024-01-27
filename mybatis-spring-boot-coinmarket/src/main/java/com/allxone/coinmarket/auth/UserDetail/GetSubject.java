package com.allxone.coinmarket.auth.UserDetail;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.allxone.coinmarket.auth.jwt.JWTAuthenticationFilter;
import com.allxone.coinmarket.auth.jwt.JWTProvider;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSubject {
	private final JWTProvider JWTProvider;

	public String getEmailByRequest(HttpServletRequest request) {
		try {
			String jwt = JWTAuthenticationFilter.getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && JWTProvider.validateToken(jwt)) {
				String email = JWTProvider.getUserNameJWT(jwt);
				return email;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;

	}
}
