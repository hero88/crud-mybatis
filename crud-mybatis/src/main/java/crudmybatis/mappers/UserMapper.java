package crudmybatis.mappers;

import crudmybatis.models.Role;
import crudmybatis.models.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    int updateUser(User user);
    int deleteUserById(long id);
    List<User> selectAllUsers();
    User selectUserById(long id);
    User selectUserByUsername(String username);
    User selectUserByEmail(String email);
    Set<Role> getRoles(String username);

    User findByResetPasswordToken(String rememberToken);

    int updateUserPassword(User user);
    boolean checkIsActive(long userId);
    User findByEmail(String email);
}
