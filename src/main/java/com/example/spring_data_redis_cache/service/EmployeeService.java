package com.example.spring_data_redis_cache.service;

import com.example.spring_data_redis_cache.dto.ApiResponse;
import com.example.spring_data_redis_cache.dto.EmployeeRequestDto;
import com.example.spring_data_redis_cache.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    ApiResponse<EmployeeResponseDto> createEmployee(EmployeeRequestDto requestDto);

    ApiResponse<List<EmployeeResponseDto>> showAll();

    ApiResponse<EmployeeResponseDto> findEmployeeByEmployeeNo(String employeeNo);

    ApiResponse<EmployeeResponseDto> updateEmployee(EmployeeRequestDto requestDto, String employeeNo);

    ApiResponse<EmployeeResponseDto> deleteEmployee(String employeeNo);
}