package com.example.spring_api_loadbalancer_nginx.controller;

import com.example.spring_api_loadbalancer_nginx.dto.StudentRequest;
import com.example.spring_api_loadbalancer_nginx.dto.StudentResponse;
import com.example.spring_api_loadbalancer_nginx.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docker")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest student) {
        StudentResponse response = service.addStudents(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> allStudents() {
        List<StudentResponse> students = service.allStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findStudentById(@PathVariable Long id) {
        StudentResponse student = service.findOneStudent(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentRequest student,
                                                         @PathVariable Long id) {
        StudentResponse response = service.updateStudent(student, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        String message = service.deleteStudent(id);
        return ResponseEntity.ok(message);
    }
}