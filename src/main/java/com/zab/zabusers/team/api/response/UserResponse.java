package com.zab.zabusers.team.api.response;

import com.zab.zabusers.team.domain.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;

    private String name;

    private String emailAddress;

    public UserResponse(User user) {
        id = user.getId();
        name = user.getName();
        emailAddress = user.getUsername();
    }
}
