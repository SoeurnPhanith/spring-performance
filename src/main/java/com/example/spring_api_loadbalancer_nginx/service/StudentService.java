package com.example.spring_api_loadbalancer_nginx.service;

import com.example.spring_api_loadbalancer_nginx.dto.StudentRequest;
import com.example.spring_api_loadbalancer_nginx.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse addStudents(StudentRequest student);
    List<StudentResponse> allStudents();
    StudentResponse findOneStudent(Long id);
    StudentResponse updateStudent(StudentRequest student, Long id);
    String deleteStudent(Long id);
}