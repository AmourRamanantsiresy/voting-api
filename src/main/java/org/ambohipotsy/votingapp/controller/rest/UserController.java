package org.ambohipotsy.votingapp.controller.rest;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.UserMapper;
import org.ambohipotsy.votingapp.model.rest.users.User;
import org.ambohipotsy.votingapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
  private UserService userService;
  private UserMapper userMapper;

  @GetMapping("/{id}")
  public User getOne(@PathVariable String id) {
    return userMapper.toRest(userService.getOne(id));
  }

  @PutMapping("/{id}")
  public User saveOne(@PathVariable String id, @RequestBody User user) {
    user.setId(id);
    return userMapper.toRest(userService.saveOne(userMapper.toDomain(user)));
  }

  @GetMapping("/whoami")
  public User whoAmI(
      @RequestAttribute("currentUser") org.ambohipotsy.votingapp.repository.entity.User user) {
    return userMapper.toRest(user);
  }
}
