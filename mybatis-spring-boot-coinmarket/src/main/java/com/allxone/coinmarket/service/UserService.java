package com.allxone.coinmarket.service;

import com.allxone.coinmarket.model.Users;

public interface UserService {
	Users changeStatusUser(Long id, Boolean status, String type);
}
