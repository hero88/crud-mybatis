package task.mapper;

import org.apache.ibatis.annotations.Param;
import task.domain.UserRole;


public interface UserRoleMapper {

    UserRole findByUserId(@Param("userId") Long userId);

}
