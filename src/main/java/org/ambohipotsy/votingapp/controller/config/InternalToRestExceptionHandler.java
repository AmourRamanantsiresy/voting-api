package org.ambohipotsy.votingapp.controller.config;

import org.ambohipotsy.votingapp.model.exceptions.ApiException;
import org.ambohipotsy.votingapp.model.utilities.RestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InternalToRestExceptionHandler {
    @ExceptionHandler(value = {ApiException.class})
    ResponseEntity<RestException> handleApiException(ApiException apiException) {
        return new ResponseEntity<>(toRest(apiException), apiException.getStatus());
    }

    private RestException toRest(ApiException apiException) {
        return RestException.builder()
                .code(apiException.getStatus().value())
                .message(apiException.getMessage())
                .status(apiException.getStatus().toString())
                .build();
    }
}
