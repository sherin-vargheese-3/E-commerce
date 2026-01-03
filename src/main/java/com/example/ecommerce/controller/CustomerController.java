package com.example.ecommerce.controller;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<AddCustomerResponseDto> addCustomer(
            @RequestBody @Valid AddCustomerRequestDto addCustomerRequestDto) {
        AddCustomerResponseDto addCustomerResponseDto = customerService.addCustomer(addCustomerRequestDto);
        return new ResponseEntity<>(addCustomerResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/{customerNumber}/orders")
    public ResponseEntity<AddOrderResponseDto> addOrder(
            @PathVariable("customerNumber") String customerNumber,
            @Valid @RequestBody AddOrderRequestDto addOrderRequestDto){
        AddOrderResponseDto addOrderResponseDto = customerService.addOrder(addOrderRequestDto, customerNumber);
        return new ResponseEntity<>(addOrderResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerProfileResponseDto> getCustomer(@PathVariable String customerNumber) {
        CustomerProfileResponseDto customerProfileResponseDto = customerService.getCustomer(customerNumber);
        return new ResponseEntity<>(customerProfileResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{customerNumber}/orders")
    public ResponseEntity<List<ListOrdersDto>> getOrders(@PathVariable String customerNumber) {
        List<ListOrdersDto> listOrdersDto = customerService.getOrders(customerNumber);
        return new ResponseEntity<>(listOrdersDto, HttpStatus.OK);
    }
}




