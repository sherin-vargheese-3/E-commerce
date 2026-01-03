package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ApiErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
