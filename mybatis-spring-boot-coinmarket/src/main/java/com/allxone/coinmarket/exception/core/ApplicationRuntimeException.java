package com.allxone.coinmarket.exception.core;

import org.springframework.http.HttpStatus;

public class ApplicationRuntimeException extends RuntimeException {
    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;


    public ApplicationRuntimeException(String message) {
        super(message);
    }
}
