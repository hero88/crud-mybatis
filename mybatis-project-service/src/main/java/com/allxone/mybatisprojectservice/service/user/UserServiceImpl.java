package com.allxone.mybatisprojectservice.service.user;

import com.allxone.mybatisprojectservice.dto.user.UserDTO;
import com.allxone.mybatisprojectservice.model.Users;
import com.allxone.mybatisprojectservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users findByUserId(Long userId) {
        return usersRepository.findById(userId);
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        List<Users> users = usersRepository.findAllUser();
        return users.stream()
                .map(Users::toUserDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateUser(Users user) {
        usersRepository.updateUser(user);
    }

    @Transactional
    @Override
    public void activeUser(Users user) {
        usersRepository.activeUser(user);
    }

    @Transactional
    @Override
    public void insertUser(Users user) {
        usersRepository.insertUser(user);
    }
}
