package com.allxone.coinmarket.exception.auth;


import com.allxone.coinmarket.exception.core.ApplicationRuntimeException;
import org.springframework.http.HttpStatus;

public class AuthenticateException extends ApplicationRuntimeException {

    public AuthenticateException(String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED;
    }


}