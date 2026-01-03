package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CustomerProfileResponseDto {
    private String customerNumber;
    private String Name;
    private String email;
    private LocalDateTime registrationDate;
}
