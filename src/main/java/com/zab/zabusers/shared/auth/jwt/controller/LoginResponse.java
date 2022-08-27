package com.zab.zabusers.shared.auth.jwt.controller;

import com.zab.zabusers.team.domain.entity.User;
import com.zab.zabusers.team.api.response.UserResponse;
import lombok.Getter;

@Getter
public class LoginResponse {

    private String jwt;
    private UserResponse user;

    public LoginResponse(User user, String jwt) {
        this.user = new UserResponse(user);
        this.jwt = jwt;
    }
}
