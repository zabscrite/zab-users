package com.zab.zabusers.team.domain.signup;

import com.zab.zabusers.team.domain.User;
import com.zab.zabusers.team.domain.UserRepository;
import com.zab.zabusers.team.domain.signup.exception.SignupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private SignUpValidator validator;

    @Autowired
    private TeamGeneratorService teamService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User signUp(SignUpCommand command) throws SignupException {
        validator.validate(command);

        User user = createUser(command);
        teamService.establishTeamFor(user);

        return user;
    }

    private User createUser(SignUpCommand command) {
        User user = command.getUser();
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        userRepository.save(user);

        return user;
    }
}
