package org.ambohipotsy.votingapp.controller.config;

import org.ambohipotsy.votingapp.model.exceptions.ApiException;
import org.ambohipotsy.votingapp.model.utilities.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InternalToRestExceptionHandler {
    @ExceptionHandler(value = {ApiException.class, RuntimeException.class})
    ResponseEntity<RestException> handleApiException(RuntimeException exception) {
        if (exception instanceof ApiException apiException) {
            return new ResponseEntity<>(toRest(apiException), apiException.getStatus());
        }
        return new ResponseEntity<>(toRest(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private RestException toRest(ApiException apiException) {
        return RestException.builder()
                .code(apiException.getStatus().value())
                .message(apiException.getMessage())
                .status(apiException.getStatus().toString())
                .build();
    }

    private RestException toRest(RuntimeException runtimeException) {
        return RestException.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(runtimeException.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .build();
    }
}
