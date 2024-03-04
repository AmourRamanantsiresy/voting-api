package org.ambohipotsy.votingapp.controller.validator;

import static org.ambohipotsy.votingapp.utils.ValidatorUtilities.isStringValid;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.rest.users.SignUser;
import org.ambohipotsy.votingapp.model.rest.users.User;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidator {

  public void validate(SignUser user) {
    StringBuilder error = new StringBuilder();

    if (!isStringValid(user.getUsername())) {
      error.append("User username is mandatory");
    }
    if (!isStringValid(user.getPassword())) {
      error.append("User password is mandatory");
    }

    if (!error.isEmpty()) {
      throw new BadRequestException(error.toString());
    }
  }

  public void validate(User user) {
    StringBuilder error = new StringBuilder();
    if (!isStringValid(user.getUsername())) {
      error.append("User username is mandatory");
    }
    if (!error.isEmpty()) {
      throw new BadRequestException(error.toString());
    }
  }
}
