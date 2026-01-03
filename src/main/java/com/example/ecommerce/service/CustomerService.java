package com.example.ecommerce.service;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.enums.Status;
import com.example.ecommerce.exception.CustomerNotFoundException;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public AddCustomerResponseDto addCustomer(AddCustomerRequestDto addCustomerRequestDto) {
        Customer customer = Customer.builder()
                .customerNumber(addCustomerRequestDto.getCustomerNumber())
                .firstName(addCustomerRequestDto.getFirstName())
                .lastName(addCustomerRequestDto.getLastName())
                .email(addCustomerRequestDto.getEmail())
                .registrationDate(LocalDateTime.now())
                .build();

        customerRepository.save(customer);

        return AddCustomerResponseDto.builder()
                .customerNumber(addCustomerRequestDto.getCustomerNumber())
                .firstName(addCustomerRequestDto.getFirstName())
                .lastName(addCustomerRequestDto.getLastName())
                .email(addCustomerRequestDto.getEmail())
                .registrationDate(LocalDateTime.now())
                .build();
    }

    public AddOrderResponseDto addOrder(AddOrderRequestDto addOrderRequestDto, String customerNumber) {
        Customer customer = customerRepository.findByCustomerNumber(customerNumber);

        if(customer == null) {
            throw new CustomerNotFoundException("Customer not found with customerNumber: " + customerNumber);
        }

        Order order = Order.builder()
                .orderNumber(addOrderRequestDto.getOrderNumber())
                .orderDate(LocalDateTime.now())
                .status(Status.PENDING)
                .totalAmount(addOrderRequestDto.getTotalAmount())
                .customer(customer)
                .build();

        orderRepository.save(order);

        return AddOrderResponseDto.builder()
                .orderNumber(order.getOrderNumber())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .customer(customer)
                .build();
    }

    public CustomerProfileResponseDto getCustomer(String customerNumber) {
        Customer customer = customerRepository.findByCustomerNumber(customerNumber);

        if(customer == null) {
            throw new CustomerNotFoundException("Customer not found with customerNumber: " + customerNumber);
        }

        return CustomerProfileResponseDto.builder()
                .customerNumber(customer.getCustomerNumber())
                .Name(customer.getFirstName() + " " + customer.getLastName())
                .email(customer.getEmail())
                .registrationDate(customer.getRegistrationDate())
                .build();
    }

    public List<ListOrdersDto> getOrders(String customerNumber) {
        Customer customer = customerRepository.findByCustomerNumber(customerNumber);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with customerNumber: " + customerNumber);
        }

        List<Order> orders = customer.getOrders();

        return orders.stream()
                .map(order -> ListOrdersDto.builder()
                        .orderNumber(order.getOrderNumber())
                        .status(order.getStatus())
                        .orderDate(order.getOrderDate())
                        .totalAmount(order.getTotalAmount())
                        .build())
                .collect(Collectors.toList());

    }
}