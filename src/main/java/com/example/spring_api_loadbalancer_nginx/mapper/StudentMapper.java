package com.example.spring_api_loadbalancer_nginx.mapper;

import com.example.spring_api_loadbalancer_nginx.dto.StudentRequest;
import com.example.spring_api_loadbalancer_nginx.dto.StudentResponse;
import com.example.spring_api_loadbalancer_nginx.entity.Student;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    /// This method is use for map data from request dto to entity
    public Student toEntity(StudentRequest dto){
        Student student = new Student();

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setSubject(dto.getSubject());

        return student;
    }


    /// This method is use for map data from entity or model to response dto
    public StudentResponse toDto(Student student){
        StudentResponse dto = new StudentResponse();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setSubject(student.getSubject());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());


        return dto;
    }


    /// this method is use for update data
    /// update Entity from Request DTO
    public void updateEntity(Student student, StudentRequest dto){
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setSubject(dto.getSubject());
    }

}
