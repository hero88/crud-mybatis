package sample.mybatis.xml.mapper;

import org.apache.ibatis.annotations.Param;
import sample.mybatis.xml.domain.UserRole;

public interface UserRoleMapper {

    UserRole findByUserId(@Param("userId") Long userId);

}
