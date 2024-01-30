package com.allxone.coinmarket.service;

import com.allxone.coinmarket.dto.request.PasswordDTO;
import com.allxone.coinmarket.model.Users;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
	Users changeStatusUser(Long id, Boolean status, String type);

	Users getOneUserByUsername(String username);

	List<Users> getAllUser();

	Users getOneUserById(Long id);

	boolean createUser(Users user);

	Users updateUser(Long id,Users user);

	boolean deleteUser(Long id);

	int changePassword(PasswordDTO passwordDTO, HttpServletRequest request);
}
