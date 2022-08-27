package com.zab.zabusers.shared.auth.jwt.domain;

import com.zab.zabusers.team.domain.Team;
import com.zab.zabusers.team.domain.TeamRepository;
import com.zab.zabusers.team.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGeneratorService {

    @Autowired
    private TeamRepository teamRepository;

    @Value("${jwt.token_validity}")
    private long jwtTokenValidityMs;

    @Value("${jwt.secret_key}")
    private String jwtSecretKey;

    public String generateToken(JwtUserDetails jwtUserDetails) {
        User user = jwtUserDetails.getUser();
        Map<String, Object> claims = getClaims(user, jwtUserDetails.getTeam());
        Date expirationDate = new Date(System.currentTimeMillis() + jwtTokenValidityMs);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

    private Map<String, Object> getClaims(User user, Team team) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("team", getTeamClaims(team));
        return claims;
    }

    private Map<String, Object> getTeamClaims(Team team) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", team.getId());
        claims.put("name", team.getName());

        return claims;
    }
}
