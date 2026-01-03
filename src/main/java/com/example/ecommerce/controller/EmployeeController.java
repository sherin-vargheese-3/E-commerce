package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddEmployeeRequestDto;
import com.example.ecommerce.dto.AddEmployeeResponseDto;
import com.example.ecommerce.dto.ListEmployeeResponseDto;
import com.example.ecommerce.model.Employee;
import com.example.ecommerce.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<AddEmployeeResponseDto> addEmployee(
            @RequestBody @Valid AddEmployeeRequestDto addEmployeeRequestDto
    ){
        AddEmployeeResponseDto addEmployeeResponseDto = employeeService.addEmployee(addEmployeeRequestDto);
        return new ResponseEntity<>(addEmployeeResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListEmployeeResponseDto>> getAll() {
        List<ListEmployeeResponseDto> listEmployeeResponseDtos = employeeService.getAll();
        return new ResponseEntity<>(listEmployeeResponseDtos, HttpStatus.OK);
    }

    @GetMapping("high-salary")
    public ResponseEntity<AddEmployeeResponseDto> highSalary() {
        AddEmployeeResponseDto addEmployeeResponseDto = employeeService.highSalary();
        return new ResponseEntity<>(addEmployeeResponseDto, HttpStatus.OK);
    }

    @GetMapping("/average-salary")
    public String averageSalary() {
        return employeeService.averageSalary();
    }

    @GetMapping("group-by-department")
    public Map<String, List<AddEmployeeResponseDto>> groupByDepartment() {
       return employeeService.groupByDepartment();
    }

    @GetMapping("/sorted-by-salary")
    public List<AddEmployeeResponseDto> getEmployeesSortedBySalary() {
        return employeeService.getEmployeesSortedBySalary();
    }

    @GetMapping("/top-earners")
    public List<AddEmployeeResponseDto> getTopEarners(
            @RequestParam(defaultValue = "3") int limit
    ) {
        return employeeService.getTopEarners(limit);
    }
}
