package com.example.demo_spring_boot_docker.controller;

import com.example.demo_spring_boot_docker.entity.Students;
import com.example.demo_spring_boot_docker.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Students> allStudent(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Students oneStudent(@PathVariable Long id){
        Students students =  studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("student not founnd!"));
        return students;
    }

    @PostMapping
    public Students addStudent(@RequestBody Students students){
        return studentRepository.save(students);
    }

    @DeleteMapping("/{id}")
    public String deleteStudents(@PathVariable Long id){
        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("student not found!"));

        studentRepository.delete(students);
        return "Delete student success!";
    }

}
