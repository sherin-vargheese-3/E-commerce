package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddEmployeeRequestDto;
import com.example.ecommerce.dto.AddEmployeeResponseDto;
import com.example.ecommerce.dto.ListEmployeeResponseDto;
import com.example.ecommerce.exception.EmployeeNotFoundException;
import com.example.ecommerce.model.Employee;
import com.example.ecommerce.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public AddEmployeeResponseDto addEmployee(AddEmployeeRequestDto addEmployeeRequestDto) {
        Employee employee = Employee.builder()
                .name(addEmployeeRequestDto.getName())
                .department(addEmployeeRequestDto.getDepartment())
                .salary(addEmployeeRequestDto.getSalary())
                .build();

        employeeRepository.save(employee);

        return AddEmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }

    public List<ListEmployeeResponseDto> getAll() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(this::mapToListEmployeeResponseDto)
                .toList();
    }

    public AddEmployeeResponseDto highSalary() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .map(this::mapToResponse)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public String averageSalary() {
        List<Employee> employees = employeeRepository.findAll();

        double avg = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        return "Average salary: " + avg;
    }

    public Map<String, List<AddEmployeeResponseDto>> groupByDepartment() {
        return employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(this::mapToResponse, Collectors.toList())
                ));
    }

    public List<AddEmployeeResponseDto> getEmployeesSortedBySalary() {
        return employeeRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .map(this::mapToResponse)
                .toList();
    }

    public List<AddEmployeeResponseDto> getTopEarners(int limit) {
        return employeeRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .limit(limit)
                .map(this::mapToResponse)
                .toList();
    }

    public AddEmployeeResponseDto mapToResponse(Employee employee) {
        return AddEmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .build();
    }

    public ListEmployeeResponseDto mapToListEmployeeResponseDto(Employee employee) {
        return ListEmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }
}
