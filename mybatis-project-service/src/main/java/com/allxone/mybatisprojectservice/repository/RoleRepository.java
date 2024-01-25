package com.allxone.mybatisprojectservice.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RoleRepository {

    private final SqlSession sqlSession;

    public RoleRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


}
