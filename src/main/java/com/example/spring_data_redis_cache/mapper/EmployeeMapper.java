package com.example.spring_data_redis_cache.mapper;

import com.example.spring_data_redis_cache.dto.EmployeeRequestDto;
import com.example.spring_data_redis_cache.dto.EmployeeResponseDto;
import com.example.spring_data_redis_cache.entity.EmployeeEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    //dto -> entity
    public EmployeeEntity dtoToEntity(EmployeeRequestDto requestDto){
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmployeeNo(requestDto.getEmployeeNo());
        emp.setFirstName(requestDto.getFirstName());
        emp.setMidInit(requestDto.getMidInit());
        emp.setLastName(requestDto.getLastName());
        emp.setHireDate(requestDto.getHireDate());
        emp.setJob(requestDto.getJob());
        emp.setEdLevel(requestDto.getEdLevel());
        emp.setBirthDate(requestDto.getBirthDate());
        emp.setPhoneNo(requestDto.getPhoneNo());
        emp.setSex(requestDto.getSex());
        emp.setWorkDept(requestDto.getWorkDept());
        emp.setSalary(requestDto.getSalary());
        emp.setBonus(requestDto.getBonus());
        emp.setCommission(requestDto.getCommission());

        return emp;
    }

    //entity -> dto
    public EmployeeResponseDto entityToDto(EmployeeEntity employee){
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setId(employee.getId());
        responseDto.setEmployeeNo(employee.getEmployeeNo());
        responseDto.setFirstName(employee.getFirstName());
        responseDto.setLastName(employee.getLastName());
        responseDto.setSex(employee.getSex());
        responseDto.setJob(employee.getJob());
        responseDto.setSalary(employee.getSalary());
        responseDto.setHireDate(employee.getHireDate());
        responseDto.setPhoneNo(employee.getPhoneNo());
        responseDto.setWorkDept(employee.getWorkDept());
        responseDto.setCommission(employee.getCommission());

        return responseDto;
    }

    // dto -> existing entity (update)
    public void updateEntityFromDto(EmployeeRequestDto dto, EmployeeEntity emp){
        emp.setEmployeeNo(dto.getEmployeeNo());
        emp.setFirstName(dto.getFirstName());
        emp.setMidInit(dto.getMidInit());
        emp.setLastName(dto.getLastName());
        emp.setHireDate(dto.getHireDate());
        emp.setJob(dto.getJob());
        emp.setEdLevel(dto.getEdLevel());
        emp.setBirthDate(dto.getBirthDate());
        emp.setPhoneNo(dto.getPhoneNo());
        emp.setSex(dto.getSex());
        emp.setWorkDept(dto.getWorkDept());
        emp.setSalary(dto.getSalary());
        emp.setBonus(dto.getBonus());
        emp.setCommission(dto.getCommission());
    }
}
