package com.allxone.coinmarket.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.allxone.coinmarket.exception.core.ApplicationException;
import com.allxone.coinmarket.exception.core.ApplicationRuntimeException;
import com.allxone.coinmarket.exception.core.ErrorDetails;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApplicationException.class, ApplicationRuntimeException.class})
    public ResponseEntity<ErrorDetails> handlerApplicationException(ApplicationException ex, HttpServletRequest request) {

        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .statusCode(ex.getStatus().value())
                .status(ex.getStatus().getReasonPhrase())
                .message(ex.getMessage())
                .timestamp(new Date())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(errorDetails);
    }


}
