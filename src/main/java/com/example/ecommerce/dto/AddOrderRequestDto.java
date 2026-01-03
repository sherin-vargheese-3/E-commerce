package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddOrderRequestDto {
    private String orderNumber;
    private BigDecimal totalAmount;
}
