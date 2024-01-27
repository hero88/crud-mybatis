package com.allxone.mybatisprojectservice.mapper;

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

    public Users findUserById(Long id) {
        return this.sqlSession.selectOne("findUserById", id);
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

    public void insertUser(Users user) {
        this.sqlSession.insert("insertUser", user);
    }
}
