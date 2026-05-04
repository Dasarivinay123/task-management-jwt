package com.vinay.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vinay.controller.ResourceAlreadyExistsException;
import com.vinay.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleAlreadyExists(
            ResourceAlreadyExistsException ex) {

        ApiResponse<Object> response =
                new ApiResponse<>(
                        false,
                        409,
                        ex.getMessage(),
                        null
                );

        return ResponseEntity.status(409).body(response);
    }
}
