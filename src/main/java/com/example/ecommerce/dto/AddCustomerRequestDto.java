package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddCustomerRequestDto {
    private String customerNumber;
    private String firstName;
    private String lastName;
    private String email;
}
