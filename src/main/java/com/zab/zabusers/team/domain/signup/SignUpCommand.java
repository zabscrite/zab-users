package com.zab.zabusers.team.domain.signup;

import com.zab.zabusers.team.domain.User;
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

        return user;
    }
}
