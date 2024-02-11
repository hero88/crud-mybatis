package com.allxone.mybatisprojectbackend.token;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TokenMapper {
    List<Token> findAllValidTokenByUser(Long id);
    Optional<Token> findByToken(String token);
    void saveToken(Token token);
    Token getTokenById(Long id);
    void updateToken(Token token);
}
