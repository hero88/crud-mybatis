package com.allxone.coinmarket.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.auth.UserDetail.CustomUserDetail;
import com.allxone.coinmarket.auth.jwt.JWTProvider;
import com.allxone.coinmarket.auth.request.AuthenticationRequest;
import com.allxone.coinmarket.auth.response.AuthenticationResponse;
import com.allxone.coinmarket.mapper.UsersMapper;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.model.UsersExample;
import com.allxone.coinmarket.utilities.CookiesUtils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
	
	private final UsersMapper userMapper;
	
	private final PasswordEncoder endcode;
	
	private final JWTProvider jwtProvider;
	
	private final CookiesUtils cookie;
	
	private final HttpServletResponse resp;
	
	public Map<String, String> logIn(AuthenticationRequest authReq){
		Integer status = checkStatusLogin(authReq);
		Map<String, String> map = new HashMap<>();
		map.put("status", String.valueOf(status));
		if(status == 0) {
			return map;
		}
		if(status == 1){
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			CustomUserDetail userDetail =(CustomUserDetail) authentication.getPrincipal();
			String accessToken = jwtProvider.createRefreshToken(userDetail);
			String refreshToken = jwtProvider.createToken(userDetail);
		
			Users user = userMapper.findUserByEmail(userDetail.getEmail());
			UsersExample example = new UsersExample();
			example.createCriteria().andIdEqualTo(user.getId());
			user.setRememberToken(refreshToken);
			userMapper.updateByExample(user, example);
			cookie.add("token", accessToken, 2, resp);
			
			map.put("access_token", accessToken);
			map.put("refresh_token", refreshToken);
			return map;
		}
		return map;
	}
	
	public int checkStatusLogin(AuthenticationRequest authReq) {
		UsersExample example = new UsersExample();
		example.createCriteria().andEmailEqualTo(authReq.getEmail());
		Users user = userMapper.selectByExample(example).get(0);
        if (user == null) {
            return 0;
        }else if (endcode.matches(authReq.getPassword(), user.getPassword())) {
            return 1;
        }else if (user.getIsActive() == false) {
            return 2;
        }
        else{
            return 3;
        }

    }

}
