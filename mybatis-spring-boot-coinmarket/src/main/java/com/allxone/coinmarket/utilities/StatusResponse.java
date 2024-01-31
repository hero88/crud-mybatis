package com.allxone.coinmarket.utilities;

import com.allxone.coinmarket.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StatusResponse {
    public static ResponseEntity<?> setStatus(int status) {
        switch (status){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                        .message("User dose not exist")
                        .success(false)
                        .build());
            case 1:
                return  ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                        .message("Successfully")
                        .success(true)
                        .build());
            case 2:
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                        .message("Incorrect")
                        .success(false)
                        .build());
            default:
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                        .message("Server error")
                        .success(false)
                        .build());
        }
    }
}
