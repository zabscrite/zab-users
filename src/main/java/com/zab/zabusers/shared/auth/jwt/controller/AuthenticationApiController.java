package com.zab.zabusers.shared.auth.jwt.controller;

import com.zab.zabusers.shared.auth.jwt.domain.JwtGenerator;
import com.zab.zabusers.shared.auth.jwt.domain.JwtUserDetails;
import com.zab.zabusers.team.domain.User;
import com.zab.zabusers.team.domain.UserService;
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
    private UserService userService;

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping
    public LoginResponse generateJwt(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        String jwt = jwtGenerator.generateToken(user);
        return new LoginResponse(user, jwt);
    }
}
