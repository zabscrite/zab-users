package com.zab.zabusers.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank(message="Username is required")
    private String username;

    @NotBlank(message="Password is required")
    @Password
    private String password;

    public SignUpCommand toCommand() {
        SignUpCommand command = new SignUpCommand();
        command.setUsername(username);
        command.setPassword(password);

        return command;
    }

}
