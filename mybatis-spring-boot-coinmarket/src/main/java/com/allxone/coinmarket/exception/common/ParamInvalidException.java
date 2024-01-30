package com.allxone.coinmarket.exception.common;


import com.allxone.coinmarket.exception.core.ApplicationException;
import org.springframework.http.HttpStatus;

public class ParamInvalidException extends ApplicationException {
    public ParamInvalidException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
