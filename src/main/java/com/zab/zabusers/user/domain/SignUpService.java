package com.zab.zabusers.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signUp(SignUpCommand command) throws EmailExistsException {
        if (emailAlreadyExists(command.getUsername())) {
            throw new EmailExistsException(command.getUsername());
        }

        User user = command.getUser();

        String passwordHash = passwordEncoder.encode(command.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
        return user;
    }

    private boolean emailAlreadyExists(String email) {
        User existingUser = userRepository.findByUsername(email);
        return existingUser != null;
    }
}
