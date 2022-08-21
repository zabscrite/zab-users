package com.zab.zabusers.user.controller;

import com.zab.zabusers.user.domain.SignUpService;
import com.zab.zabusers.user.domain.SignupException;
import com.zab.zabusers.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sign-up")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public UserResponse signUp(@RequestBody @Valid SignUpRequest request) throws SignupException {
        User user = signUpService.signUp(request.toCommand());
        return new UserResponse(user);
    }
}
