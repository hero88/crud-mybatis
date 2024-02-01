package com.allxone.coinmarket.exception.common;

import org.springframework.http.HttpStatus;

import com.allxone.coinmarket.exception.core.ApplicationException;

public class DuplicateDataException extends ApplicationException {

	 public DuplicateDataException(String message) {
	        super(message);
	        this.status = HttpStatus.BAD_REQUEST;
	    }
}
