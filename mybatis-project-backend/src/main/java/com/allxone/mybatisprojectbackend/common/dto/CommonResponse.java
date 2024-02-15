package com.allxone.mybatisprojectbackend.common.dto;

public record CommonResponse<T>(
        String status,
        Integer code,
        String message,
        T data
) {

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>("ok", 200, "Success response", data);
    }

    public static <T> CommonResponse<T> error(T data) {
        return new CommonResponse<>("Internal Server Error", 500, "failure response", data);
    }
}
