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

    public List<Coins> findByUserId(Long userId) {
        return this.sqlSession.selectList("findByUserId", userId);
    }

    public void updateCoin(Coins coins) {
        this.sqlSession.update("updateCoin", coins);
    }

    public void insertCoin(Coins coins) {
        this.sqlSession.insert("insertCoin", coins);
    }
}
