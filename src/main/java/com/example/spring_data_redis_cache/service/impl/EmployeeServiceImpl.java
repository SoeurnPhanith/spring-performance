package com.example.spring_data_redis_cache.service.impl;

import com.example.spring_data_redis_cache.dto.ApiResponse;
import com.example.spring_data_redis_cache.dto.EmployeeRequestDto;
import com.example.spring_data_redis_cache.dto.EmployeeResponseDto;
import com.example.spring_data_redis_cache.entity.EmployeeEntity;
import com.example.spring_data_redis_cache.mapper.EmployeeMapper;
import com.example.spring_data_redis_cache.repo.EmployeeRepository;
import com.example.spring_data_redis_cache.service.EmployeeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository empRepo;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository empRepo, EmployeeMapper mapper) {
        this.empRepo = empRepo;
        this.mapper = mapper;
    }

    @CacheEvict(value = "employees", allEntries = true)
    @Transactional
    @Override
    public ApiResponse<EmployeeResponseDto> createEmployee(EmployeeRequestDto requestDto) {
        if (empRepo.existsByEmployeeNo(requestDto.getEmployeeNo())) {
            return new ApiResponse<>(null, "Employee already exists");
        }

        EmployeeEntity emp = mapper.dtoToEntity(requestDto);
        EmployeeEntity saved = empRepo.save(emp);

        return new ApiResponse<>(mapper.entityToDto(saved), "Employee created successfully");
    }

    @Cacheable(value = "employees")
    @Override
    public ApiResponse<List<EmployeeResponseDto>> showAll() {
        List<EmployeeResponseDto> list = empRepo.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());

        return new ApiResponse<>(list, "All employees fetched successfully");
    }

    @Cacheable(value = "employee", key = "#employeeNo")
    @Override
    public ApiResponse<EmployeeResponseDto> findEmployeeByEmployeeNo(String employeeNo) {
        EmployeeEntity emp = empRepo.findByEmployeeNo(employeeNo).orElse(null);

        if (emp == null) {
            return new ApiResponse<>(null, "Employee not found");
        }
        return new ApiResponse<>(mapper.entityToDto(emp), "Employee fetched successfully");
    }

    @CacheEvict(value = {"employee", "employees"}, allEntries = true)
    @Transactional
    @Override
    public ApiResponse<EmployeeResponseDto> updateEmployee(EmployeeRequestDto requestDto, String employeeNo) {
        EmployeeEntity existing = empRepo.findByEmployeeNo(employeeNo).orElse(null);

        if (existing == null) {
            return new ApiResponse<>(null, "Employee not found");
        }

        if (!requestDto.getEmployeeNo().equals(employeeNo) &&
                empRepo.existsByEmployeeNo(requestDto.getEmployeeNo())) {
            return new ApiResponse<>(null, "Employee number already exists");
        }

        mapper.updateEntityFromDto(requestDto, existing);
        EmployeeEntity saved = empRepo.save(existing);

        return new ApiResponse<>(mapper.entityToDto(saved), "Employee updated successfully");
    }

    @CacheEvict(value = {"employee", "employees"}, allEntries = true)
    @Transactional
    @Override
    public ApiResponse<EmployeeResponseDto> deleteEmployee(String employeeNo) {
        EmployeeEntity existing = empRepo.findByEmployeeNo(employeeNo).orElse(null);

        if (existing == null) {
            return new ApiResponse<>(null, "Employee not found");
        }

        empRepo.delete(existing);
        return new ApiResponse<>(mapper.entityToDto(existing), "Employee deleted successfully");
    }
}