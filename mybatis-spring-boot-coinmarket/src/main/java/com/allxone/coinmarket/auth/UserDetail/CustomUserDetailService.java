package com.allxone.coinmarket.auth.UserDetail;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.mapper.UsersMapper;
import com.allxone.coinmarket.model.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{
	
	private final UsersMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users us = userMapper.findUserByEmail(username);
		if(us != null) {
			return UserFactory.create(us);
		}

        throw new UsernameNotFoundException("User not found with Email: " + username);
	}
}
