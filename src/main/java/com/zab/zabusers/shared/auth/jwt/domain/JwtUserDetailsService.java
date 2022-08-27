package com.zab.zabusers.shared.auth.jwt.domain;

import com.zab.zabusers.team.domain.entity.Team;
import com.zab.zabusers.team.domain.repository.TeamRepository;
import com.zab.zabusers.team.domain.entity.User;
import com.zab.zabusers.team.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TeamRepository teamRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return null;
        }

        return mapUserDetails(user.get());
    }

    public JwtUserDetails fetchDetails(Claims claims) {
        User user = userRepository.findByUsername(claims.getSubject())
                .orElse(null);

        JwtUserDetails jwtUserDetails = mapUserDetails(user);
        jwtUserDetails.setTeam(fetchTeamFromClaims(claims));

        return jwtUserDetails;
    }

    private JwtUserDetails mapUserDetails(User user) {
        return new JwtUserDetails(user);
    }

    private Team fetchTeamFromClaims(Claims claims) {
        Map<String, Object> teamDetails = (Map<String, Object>) claims.get("team");
        Long teamId = Long.parseLong(teamDetails.get("id").toString());
        // or else throw exception
        Team team = teamRepository.findById(teamId).orElse(null);
        return team;
    }
}
