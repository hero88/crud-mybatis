package com.allxone.coinmarket.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.allxone.coinmarket.mapper.UserRoleMapper;
import com.allxone.coinmarket.mapper.UsersMapper;
import com.allxone.coinmarket.model.UserRole;
import com.allxone.coinmarket.model.UserRoleExample;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.model.UsersExample;
import com.allxone.coinmarket.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UsersMapper userMapper;
	
	private final UserRoleMapper userRoleMapper;

	@Override
	public Users changeStatusUser(Long id, Boolean status, String type) {
		UsersExample example = new UsersExample();
		example.createCriteria().andIdEqualTo(id);
		Users user = userMapper.selectByExample(example).get(0);
		if(type.equalsIgnoreCase("activated")) {	
			UserRoleExample usRoleExample = new UserRoleExample();
			usRoleExample.createCriteria().andRoleIdEqualTo(2).andUserIdEqualTo(user.getId());
			user.setEmailVerificationAt(new Date());
			UserRole useRole = userRoleMapper.selectByExample(usRoleExample).get(0);
			if(useRole==null) {
				useRole = new UserRole();
				useRole.setRoleId(2);
				useRole.setUserId(user.getId());
				userRoleMapper.insert(useRole);
			}
		}
		user.setIsActive(status);
		user.setUpdatedAt(new Date());
		userMapper.updateByExample(user, example);
		return user;
	}

}
