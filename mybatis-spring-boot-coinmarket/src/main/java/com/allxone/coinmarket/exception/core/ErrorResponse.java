package com.allxone.coinmarket.exception.core;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorResponse {
    private String message;

    private Date timestamp;

    private int statusCode;

    private String status;

    private String error;

    private String path;
}
