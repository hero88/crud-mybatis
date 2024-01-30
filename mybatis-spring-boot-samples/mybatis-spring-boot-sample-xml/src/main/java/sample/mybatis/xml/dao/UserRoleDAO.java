package sample.mybatis.xml.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import sample.mybatis.xml.domain.UserRole;

@Component
public class UserRoleDAO {

    private final SqlSession sqlSession;

    public UserRoleDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UserRole selectUserByUserId(Long userId) {
        return this.sqlSession.selectOne("selectUserByUserId", userId);
    }

    public void updateUserRole(Long userId) {
        UserRole role = new UserRole(userId);
        this.sqlSession.update("updateUserRole", role);
    }

    public void createUserRole(Long userId, Long roleId) {
        UserRole role = new UserRole(userId, roleId);
        this.sqlSession.insert("createUserRole", role);
    }

}
