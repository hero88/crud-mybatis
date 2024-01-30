package com.allxone.coinmarket.service.impl;

import java.util.Date;
import java.util.List;

import com.allxone.coinmarket.auth.UserDetail.GetSubject;
import com.allxone.coinmarket.dto.request.PasswordDTO;
import com.allxone.coinmarket.model.*;
import com.allxone.coinmarket.model.enums.ERole;
import com.allxone.coinmarket.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.mapper.UserRoleMapper;
import com.allxone.coinmarket.mapper.UsersMapper;
import com.allxone.coinmarket.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UsersMapper userMapper;

	private final UserRoleMapper userRoleMapper;

	private final PasswordEncoder passwordEncoder;

	private final GetSubject getSubjectJWT;

	private final RoleService roleService;

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

	@Override
	public Users getOneUserByUsername(String username) {
		UsersExample userSQL = new UsersExample();
		userSQL.createCriteria().andUsernameEqualTo(username);
		List<Users> user = userMapper.selectByExample(userSQL);
		if(user.size() == 1 ){
			return user.get(0);
		}
		return null;
	}

	@Override
	public List<Users> getAllUser() {
		return userMapper.findAllUser();
	}

	@Override
	public boolean createUser(Users user) {
		Roles role = roleService.getRoleByName(ERole.USER);
		if(isCheckUserExistByEmail(user.getEmail()) == null) {

			user.setCreatedAt(new Date());
			user.setIsActive(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userMapper.insert(user);

			Users us = isCheckUserExistByEmail(user.getEmail());
			UserRole userRole = new UserRole();
			userRole.setRoleId(role.getId());
			userRole.setUserId(us.getId());
			userRoleMapper.insert(userRole);

			return true;
		}
		return false;
	}

	@Override
	public Users updateUser(Long id,Users user) {
		Users us = getOneUserById(id);
		if(us != null){
			us.setAge(user.getAge());
			us.setAddress(user.getAddress());
			us.setUsername(user.getUsername());
			us.setGender(user.getGender());
			us.setPhoneNumber(user.getPhoneNumber());
			us.setName(user.getName());
			us.setUpdatedAt(new Date());
			userMapper.updateUserById(us);
		}

		return us;
	}

	@Override
	public boolean deleteUser(Long id) {
		UsersExample userSQL = new UsersExample();
		UserRoleExample userRoleSQL = new UserRoleExample() ;
		userRoleSQL.createCriteria().andUserIdEqualTo(id);
		userSQL.createCriteria().andIdEqualTo(id);
		userRoleMapper.deleteByExample(userRoleSQL);
		List<Users> users = userMapper.selectByExample(userSQL);
		if(!users.isEmpty()){
			userMapper.deleteByExample(userSQL);
			return true;
		}
		return false;
	}

	/**
	 * return 0: User does not exist
	 * return 1: Password changed successfully
	 * return 2: Wrong password or password confirm
	 */
	@Override
	public int changePassword(PasswordDTO passwordDTO, HttpServletRequest request) {
		String email = getSubjectJWT.getEmailByRequest(request);
		Users user = isCheckUserExistByEmail(email);
		if (user !=null) {
			if (passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword())
					&& passwordDTO.getNewPassword().equals(passwordDTO.getPasswordConfirm())) {
				user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
				userMapper.updateUserById(user);
				return 1;
			}
			return 2;
		}
		return 0;
	}


	/**
	 * param : id user
	 * return: user if user exists || null user does not exist
	 */
	@Override
	public Users getOneUserById(Long id) {
		UsersExample userSQL = new UsersExample();
		userSQL.createCriteria().andIdEqualTo(id);
		List<Users> users = userMapper.selectByExample(userSQL);
		if(!users.isEmpty()){
			return users.get(0);
		}return null;
	}


	/**
	 * param : email
	 * return: user if user exists || null user does not exist
	 */
	public Users isCheckUserExistByEmail(String email){
		UsersExample userSQL = new UsersExample();
		userSQL.createCriteria().andEmailEqualTo(email);
		List<Users> user = userMapper.selectByExample(userSQL);
		if(!user.isEmpty()){
			return  user.get(0);
		}
		return null;
	}

}
