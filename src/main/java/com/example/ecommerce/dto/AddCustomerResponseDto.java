package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AddCustomerResponseDto {
    private String customerNumber;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime registrationDate;
}
