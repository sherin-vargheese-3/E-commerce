package com.example.ecommerce.dto;

import com.example.ecommerce.enums.Status;
import com.example.ecommerce.model.Customer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class AddOrderResponseDto {
    private String orderNumber;
    private LocalDateTime orderDate;
    private Status status;
    private BigDecimal totalAmount;
    private Customer customer;
}
