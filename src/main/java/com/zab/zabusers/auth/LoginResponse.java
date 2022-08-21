package com.zab.zabusers.auth;

import com.zab.zabusers.user.User;
import com.zab.zabusers.user.UserResponse;
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
