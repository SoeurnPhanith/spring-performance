package com.example.spring_api_loadbalancer_nginx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class StudentRequest {

    private String name;
    private String subject;
    private String email;

}
