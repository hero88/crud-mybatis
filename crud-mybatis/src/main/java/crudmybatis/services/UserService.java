package crudmybatis.services;

import crudmybatis.exception.UserNotFoundException;
import crudmybatis.mappers.UserMapper;
import crudmybatis.models.Role;
import crudmybatis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int deleteUserById(long userId) {
        return userMapper.deleteUserById(userId);
    }

    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    public User findById(long userId) {
        return userMapper.selectUserById(userId);
    }

    public User findByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    public User findByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userMapper.selectUserByUsername(username) != null;
    }

    public boolean existsByEmail(String email) {
        return userMapper.selectUserByEmail(email) != null;
    }

    public Set<Role> getRoles(String username) {
        return userMapper.getRoles(username);
    }

    public User findByResetPasswordToken(String remember_token){return userMapper.findByResetPasswordToken(remember_token);}

    public void updateResetPasswordToken(String rememberToken, String email) throws UserNotFoundException {
        User user = userMapper.findByEmail(email);
        if (user != null) {
            user.setRememberToken(rememberToken);
            userMapper.updateUser(user);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String remember_token) {
        return userMapper.findByResetPasswordToken(remember_token);
    }

    public void updatePassword(User user) {
        userMapper.updateUser(user);
    }
}
