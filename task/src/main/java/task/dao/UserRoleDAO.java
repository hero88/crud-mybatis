package task.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import task.domain.UserRole;


@Component
public class UserRoleDAO {

    private final SqlSession sqlSession;

    public UserRoleDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UserRole selectUserRoleByUserId(Long userId) {
        return this.sqlSession.selectOne("selectUserRoleByUserId", userId);
    }
}
