package com.allxone.coinmarket.exception.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ApplicationException extends Exception {

    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;


    public ApplicationException(String message) {
        super(message);
    }
}
