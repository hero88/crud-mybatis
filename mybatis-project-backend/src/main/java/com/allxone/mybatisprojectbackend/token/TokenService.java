package com.allxone.mybatisprojectbackend.token;

import com.allxone.mybatisprojectbackend.model.Coin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenMapper tokenMapper;

    public List<Token> findAllValidTokenByUser(Long id){
        return tokenMapper.findAllValidTokenByUser(id);
    }

    public void saveAllTokens(List<Token> tokens){
        tokens.forEach(tokenMapper::saveToken);
    }

    public Token saveToken(Token token) {
        tokenMapper.saveToken(token);
        return getTokenById(token.getId());
    }

    public Token updateToken(Token token) {
        tokenMapper.updateToken(token);
        return getTokenById(token.getId());
    }

    public Optional<Token> findByToken(String token){
        return tokenMapper.findByToken(token);
    };

    public Token getTokenById(Long id){
        return tokenMapper.getTokenById(id);
    }
}
