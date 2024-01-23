package sample.mybatis.xml.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import sample.mybatis.xml.domain.Users;

@Component
public class UsersDAO {

    private final SqlSession sqlSession;

    public UsersDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Users selectUserByUserName(String userName) {
        return this.sqlSession.selectOne("selectUserByUserName", userName);
    }

    public void updateUser(int userId, String userName, String password) {
        Users users = new Users(userId, userName, password);
        this.sqlSession.update("updateUser", users);
    }




}
