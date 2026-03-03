package com.example.spring_data_redis_cache.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pn_employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeNo;
    private String firstName;
    private String midInit;
    private String lastName;
    private String workDept;
    private String phoneNo;
    private Date hireDate;
    private String job;
    private String edLevel;
    private String sex;
    private Date birthDate;
    private BigDecimal salary;
    private Double bonus;
    private Double commission;

}
