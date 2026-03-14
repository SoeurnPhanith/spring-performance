package com.example.spring_api_loadbalancer_nginx.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String locale; // store Locale as String
}
