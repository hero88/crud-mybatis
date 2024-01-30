package com.allxone.coinmarket.exception.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private Date timestamp;

    private String message;

    private int statusCode;

    private String status;

    private String exceptionClass;

    private String path;

}
