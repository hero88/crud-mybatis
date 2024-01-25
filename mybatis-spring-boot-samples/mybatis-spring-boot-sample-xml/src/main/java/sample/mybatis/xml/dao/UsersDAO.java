package sample.mybatis.xml.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import sample.mybatis.xml.domain.City;
import sample.mybatis.xml.domain.Users;

import java.util.List;

@Component
public class UsersDAO {

    private final SqlSession sqlSession;

    public UsersDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Users selectUserById(Long userId) {
        return this.sqlSession.selectOne("selectUserById", userId);
    }

    public Users selectUserByUserName(String userName) {
        return this.sqlSession.selectOne("selectUserByUserName", userName);
    }
}
