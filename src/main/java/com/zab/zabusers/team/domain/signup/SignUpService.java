package com.zab.zabusers.team.domain.signup;

import com.zab.zabusers.team.domain.User;
import com.zab.zabusers.team.domain.UserRepository;
import com.zab.zabusers.team.domain.signup.exception.SignupException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        userRepository.save(user);

        teamService.establishTeamFor(user);

        return user;
    }

    private User createUser(SignUpCommand command) {
        User user = command.getUser();
        user.setPassword(passwordEncoder.encode(command.getPassword()));

        String name = extractInitialNameFromEmailAddress(command.getEmailAddress());
        user.setName(name);

        return user;
    }

    private String extractInitialNameFromEmailAddress(String name) {
        String username = name.split("@")[0];
        String sanitizedUsername = username.replaceAll("[^a-zA-Z\\d]", " ");
        return Arrays.stream(sanitizedUsername.split(" "))
                .map(s -> StringUtils.capitalize(s))
                .collect(Collectors.joining(" "));
    }
}
