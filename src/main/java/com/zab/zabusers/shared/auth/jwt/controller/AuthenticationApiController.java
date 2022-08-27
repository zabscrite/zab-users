package com.zab.zabusers.shared.auth.jwt.controller;

import com.zab.zabusers.shared.auth.jwt.domain.JwtGeneratorService;
import com.zab.zabusers.shared.auth.jwt.domain.JwtUserDetails;
import com.zab.zabusers.team.domain.entity.Team;
import com.zab.zabusers.team.domain.repository.TeamRepository;
import com.zab.zabusers.team.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationApiController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGeneratorService jwtGeneratorService;

    @Autowired
    private TeamRepository teamRepository;

    @PostMapping
    public LoginResponse generateJwt(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        User user = jwtUserDetails.getUser();
        Team team = teamRepository.findByOwner(user)
                .orElse(null);
        jwtUserDetails.setTeam(team);

        String jwt = jwtGeneratorService.generateToken(jwtUserDetails);
        return new LoginResponse(user, jwt);
    }
}
