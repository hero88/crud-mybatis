package com.allxone.mybatisprojectbackend.exception.exceptionHandler;

import com.allxone.mybatisprojectbackend.exception.JwtExpirationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(JwtExpirationException.class)
    public ResponseEntity<String> handleJwtExpirationException(JwtExpirationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
