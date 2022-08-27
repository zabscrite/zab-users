package com.zab.zabusers.team.domain.signup;

import com.zab.zabusers.team.domain.entity.User;
import com.zab.zabusers.team.domain.repository.UserRepository;
import com.zab.zabusers.team.domain.signup.exception.EmailExistsException;
import com.zab.zabusers.team.domain.signup.exception.SignupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignUpValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(SignUpCommand command) throws SignupException {
        Optional<User> existingUser = userRepository.findByUsername(command.getUsername());
        if (existingUser.isPresent()) {
            throw new EmailExistsException(command.getUsername());
        }
    }
}
