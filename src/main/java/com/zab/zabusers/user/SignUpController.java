package com.zab.zabusers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public UserResponse signUp(@RequestBody @Valid SignUpRequest request) {
        User user = signUpService.signUp(request.toCommand());
        return new UserResponse(user);
    }
}
