package org.ikigaidigital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
    private Instant timestamp;

    public static <T> ApiResponse<T> success(T data,String message) {
        return new ApiResponse<T>("success", message, data, Instant.now());
    }
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<T>("success", message, null, Instant.now());
    }
}
