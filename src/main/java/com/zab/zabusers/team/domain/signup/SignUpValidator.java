package com.zab.zabusers.team.domain.signup;

import com.zab.zabusers.team.domain.User;
import com.zab.zabusers.team.domain.UserRepository;
import com.zab.zabusers.team.domain.signup.exception.EmailExistsException;
import com.zab.zabusers.team.domain.signup.exception.SignupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(SignUpCommand command) throws SignupException {
        User existingUser = userRepository.findByUsername(command.getUsername());
        if (existingUser != null) {
            throw new EmailExistsException(command.getUsername());
        }
    }
}
