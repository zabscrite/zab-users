package com.zab.zabusers.auth;

import com.zab.zabusers.user.User;
import lombok.Getter;

@Getter
public class LoginResponse {

    private String jwt;
    private LoginUserResponse user;

    public LoginResponse(User user, String jwt) {
        this.user = new LoginUserResponse(user);
        this.jwt = jwt;
    }
}
