package org.ambohipotsy.votingapp.model.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

  public BadRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
