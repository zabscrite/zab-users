package com.zab.zabusers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User fetchByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
