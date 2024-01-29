package crudmybatis.mappers;

import crudmybatis.models.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int insertUserRole(UserRole userRole);

    int deleteUserRoleByUserId(long id);
}
