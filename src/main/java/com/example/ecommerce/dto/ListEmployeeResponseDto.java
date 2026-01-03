package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListEmployeeResponseDto {
    private Long id;
    private String name;
    private String department;
    private double salary;
}

