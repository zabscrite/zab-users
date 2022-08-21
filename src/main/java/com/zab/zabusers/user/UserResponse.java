package com.zab.zabusers.user;

import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;

    private String username;

    public UserResponse(User user) {
        id = user.getId();
        username = user.getUsername();
    }
}
