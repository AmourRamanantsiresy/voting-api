package org.ambohipotsy.votingapp.model.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {

  public ForbiddenException(String message) {
    super(message, HttpStatus.FORBIDDEN);
  }
}
