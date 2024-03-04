package org.ambohipotsy.votingapp.service;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.UserMapper;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.users.AuthenticationResponse;
import org.ambohipotsy.votingapp.repository.UserRepository;
import org.ambohipotsy.votingapp.repository.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private JwtService jwtService;
  private AuthenticationManager authenticationManager;
  private UserMapper userMapper;

  public AuthenticationResponse signUp(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    String jwtToken = jwtService.generateToken(savedUser);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .user(userMapper.toRest(savedUser))
        .build();
  }

  public AuthenticationResponse signIn(User user) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    User userReference =
        userRepository
            .findByUsername(user.getUsername())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "User with username = " + user.getUsername() + " not found."));
    String jwtToken = jwtService.generateToken(userReference);
    return AuthenticationResponse.builder()
        .user(userMapper.toRest(userReference))
        .token(jwtToken)
        .build();
  }
}
