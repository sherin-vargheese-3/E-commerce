package com.example.ecommerce.dto;

import com.example.ecommerce.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ListOrdersDto {
    private String orderNumber;
    private LocalDateTime orderDate;
    private Status status;
    private BigDecimal totalAmount;
}
