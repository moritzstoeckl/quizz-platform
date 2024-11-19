package com.quiz_platform.controller.advice;

import com.quiz_platform.authenticationdb.service.exception.UnauthorizedAccessException;
import com.quiz_platform.controller.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<AuthResponse> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        AuthResponse response = new AuthResponse(null, ex.getMessage(), true);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
