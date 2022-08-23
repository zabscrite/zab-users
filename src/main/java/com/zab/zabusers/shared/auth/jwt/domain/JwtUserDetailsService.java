package com.zab.zabusers.shared.auth.jwt.domain;

import com.zab.zabusers.team.domain.User;
import com.zab.zabusers.team.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        return mapUserDetails(user);
    }

    private UserDetails mapUserDetails(User user) {
        return new JwtUserDetails(user);
    }

}
