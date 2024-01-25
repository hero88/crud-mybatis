package com.allxone.mybatisprojectservice.repository;

import com.allxone.mybatisprojectservice.model.Coins;
import com.allxone.mybatisprojectservice.model.Users;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinRepository {

    private final SqlSession sqlSession;

    public CoinRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Coins findById(Long id) {
        return this.sqlSession.selectOne("findById", id);
    }

    public Coins findByName(String name) {
        return this.sqlSession.selectOne("findByName", name);
    }

    public List<Coins> findByUserId() {
        return this.sqlSession.selectList("findByUserId");
    }

    public void updateCoin(Coins coins) {
        this.sqlSession.update("updateCoin", coins);
    }

    public void createCoin(Coins coins) {
        this.sqlSession.insert("createCoin", coins);
    }
}
