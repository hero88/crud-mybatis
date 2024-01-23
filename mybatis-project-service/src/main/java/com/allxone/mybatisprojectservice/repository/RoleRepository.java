package com.allxone.mybatisprojectservice.repository;

import com.allxone.mybatisprojectservice.dto.RoleDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RoleRepository {

    private final SqlSession sqlSession;

    public RoleRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<RoleDTO> findAllRoleDTO() {
        return this.sqlSession.selectList("findAllRoleDTO");
    }

}
