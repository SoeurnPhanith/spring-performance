package com.example.spring_data_redis_cache.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmployeeRequestDto {

    @NotBlank(message = "Employee No is required")
    private String employeeNo;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String midInit;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Department is required")
    private String workDept;

    @Pattern(regexp = "^[0-9]{8,15}$", message = "Phone number must be numeric")
    private String phoneNo;

    @NotNull(message = "Hire date is required")
    private Date hireDate;

    @NotBlank(message = "Job is required")
    private String job;

    @NotBlank(message = "Education level is required")
    private String edLevel;

    @NotBlank(message = "Sex is required")
    private String sex;

    @NotNull(message = "Birth date is required")
    private Date birthDate;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private BigDecimal salary;

    private Double bonus;
    private Double commission;
}