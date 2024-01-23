package com.allxone.mybatisprojectservice.service.user;

import com.allxone.mybatisprojectservice.dto.UserDTO;
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
    public Users getByUserName(String userName) {
        return usersRepository.findByUserName(userName);
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
    public void createUser(Users user) {
        usersRepository.createUser(user);
    }
}
