package com.allxone.mybatisprojectservice.repository;

import com.allxone.mybatisprojectservice.model.Users;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UsersRepository {

    private final SqlSession sqlSession;

    public UsersRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Users findById(Long userId) {
        return this.sqlSession.selectOne("findById", userId);
    }

    public Users findByEmail(String userName) {
        return this.sqlSession.selectOne("findByEmail", userName);
    }

    public List<Users> findAllUser() {
        return this.sqlSession.selectList("findAllUser");
    }

    public void updateUser(Users user) {
        this.sqlSession.update("updateUser", user);
    }

    public void activeUser(Users user) {
        this.sqlSession.update("activeUser", user);
    }

    public void createUser(Users user) {
        this.sqlSession.insert("createUser", user);
    }
}
