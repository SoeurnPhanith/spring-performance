package com.example.spring_data_redis_cache.controller;

import com.example.spring_data_redis_cache.dto.ApiResponse;
import com.example.spring_data_redis_cache.dto.EmployeeRequestDto;
import com.example.spring_data_redis_cache.dto.EmployeeResponseDto;
import com.example.spring_data_redis_cache.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caching")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
        return employeeService.createEmployee(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<EmployeeResponseDto>> getAllEmployees() {
        return employeeService.showAll();
    }

    @GetMapping("/{employeeNo}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<EmployeeResponseDto> getEmployee(@PathVariable String employeeNo) {
        return employeeService.findEmployeeByEmployeeNo(employeeNo);
    }

    @PutMapping("/{employeeNo}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<EmployeeResponseDto> updateEmployee(
            @PathVariable String employeeNo,
            @Valid @RequestBody EmployeeRequestDto dto
    ) {
        return employeeService.updateEmployee(dto, employeeNo);
    }

    @DeleteMapping("/{employeeNo}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<EmployeeResponseDto> deleteEmployee(@PathVariable String employeeNo) {
        return employeeService.deleteEmployee(employeeNo);
    }
}