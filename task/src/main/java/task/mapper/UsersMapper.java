package task.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import task.domain.Users;


@Mapper
public interface UsersMapper {

    Users findByUserName(@Param("userName") String userName);

}
