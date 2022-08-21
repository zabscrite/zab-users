package com.zab.zabusers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(SignUpCommand command) {
        User user = command.getUser();
        userRepository.save(user);
        return user;
    }
}
