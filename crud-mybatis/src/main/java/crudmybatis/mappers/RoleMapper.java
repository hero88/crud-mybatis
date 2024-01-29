package crudmybatis.mappers;

import crudmybatis.models.Role;
import crudmybatis.models.User;
import crudmybatis.models.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface RoleMapper {
    Role selectRoleById(long id);
    Role selectRoleByName(String name);
}
