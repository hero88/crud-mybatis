package com.allxone.coinmarket.auth.jwt;

import java.util.Date;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.allxone.coinmarket.auth.UserDetail.CustomUserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTProvider {
	@Value("${jwt.secret}")
	private String JWT_SECRET;

	@Value("${jwt.expiration}")
	private long JWT_EXPIRATION;

	@Value("${jwt.refreshtoken.expiration}")
	private long JWT_REFRESHTOKEN_EXPIRATION;

	public String createToken(CustomUserDetail userDetail) {
		return buildToken(userDetail,JWT_EXPIRATION);
	}

	public String createRefreshToken(CustomUserDetail userDetail) {
		return buildToken(userDetail,JWT_REFRESHTOKEN_EXPIRATION);
	}

	private String buildToken(CustomUserDetail userDetail, long expiration) {
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + expiration);
		return Jwts
				.builder()
				.setSubject(userDetail.getEmail())
				.claim("Role", userDetail.getAuthorities())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET) 
				.compact();
	}
	
	public String getUserNameJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

	public CustomUserDetail getCurrentAuthenticatedAccount(){
		UsernamePasswordAuthenticationToken authenticationToken
				= (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetail = (CustomUserDetail) authenticationToken.getPrincipal();
		if(userDetail == null) {
			throw new EntityNotFoundException();
		}
		return  userDetail;
	}
}
