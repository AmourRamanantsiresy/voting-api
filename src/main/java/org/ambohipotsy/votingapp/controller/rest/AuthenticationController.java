package org.ambohipotsy.votingapp.controller.rest;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.UserMapper;
import org.ambohipotsy.votingapp.controller.validator.UserValidator;
import org.ambohipotsy.votingapp.model.rest.users.AuthenticationResponse;
import org.ambohipotsy.votingapp.model.rest.users.SignUser;
import org.ambohipotsy.votingapp.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private UserMapper userMapper;
    private UserValidator userValidator;
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public AuthenticationResponse singUp(
            @RequestBody SignUser signUser
    ) {
        userValidator.validate(signUser);
        return authenticationService.signUp(userMapper.toDomain(signUser));
    }

    @PostMapping("/signin")
    public AuthenticationResponse signIn(
            @RequestBody SignUser signUser
    ) {
        userValidator.validate(signUser);
        return authenticationService.signIn(userMapper.toDomain(signUser));
    }
}
