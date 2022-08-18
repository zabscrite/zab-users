package com.zab.zabusers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {

    @Value("${jwt.token_validity}")
    public long jwtTokenValidityMs;

    @Value("${jwt.secret_key}")
    private String jwtSecretKey;

    public String generateToken(UserDetails user) {
        Map<String, Object> claims = getClaims(user);
        Date expirationDate = new Date(System.currentTimeMillis() + jwtTokenValidityMs * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

    private Map<String, Object> getClaims(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        return claims;
    }
}
