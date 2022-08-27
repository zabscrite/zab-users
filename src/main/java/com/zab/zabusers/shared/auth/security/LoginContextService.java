package com.zab.zabusers.shared.auth.security;

import com.zab.zabusers.shared.auth.jwt.domain.JwtUserDetails;
import com.zab.zabusers.team.domain.Team;
import com.zab.zabusers.team.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginContextService {

    public User getCurrentUser() {
        return getJwt().getUser();
    }

    public Team getCurrentTeam() {
        return getJwt().getTeam();
    }


    private JwtUserDetails getJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwt = (JwtUserDetails) authentication.getPrincipal();
        return jwt;
    }
}
