package com.zab.zabusers.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpCommand {

    private String username;

    private String password;

    public User getUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }
}
