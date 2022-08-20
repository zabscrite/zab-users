package com.zab.zabusers.auth;

import com.zab.zabusers.user.User;
import lombok.Getter;

@Getter
public class LoginUserResponse {

    private Long id;

    private String username;

    public LoginUserResponse(User user) {
        id = user.getId();
        username = user.getUsername();
    }
}
