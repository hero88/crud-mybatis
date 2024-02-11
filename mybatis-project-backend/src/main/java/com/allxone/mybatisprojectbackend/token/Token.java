package com.allxone.mybatisprojectbackend.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    public Long id;
    public String token;
    public TokenType tokenType = TokenType.BEARER;
    public boolean revoked;
    public boolean expired;
    public Long userId;
}
