package com.example.spring_data_redis_cache.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmployeeResponseDto {

    private Long id;
    private String employeeNo;
    private String firstName;
    private String lastName;
    private String workDept;
    private String phoneNo;
    private Date hireDate;
    private String job;
    private String sex;
    private BigDecimal salary;
    private Double commission;

}
