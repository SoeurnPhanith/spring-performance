package com.example.spring_api_loadbalancer_nginx.service.impl;

import com.example.spring_api_loadbalancer_nginx.dto.StudentRequest;
import com.example.spring_api_loadbalancer_nginx.dto.StudentResponse;
import com.example.spring_api_loadbalancer_nginx.entity.Student;
import com.example.spring_api_loadbalancer_nginx.mapper.StudentMapper;
import com.example.spring_api_loadbalancer_nginx.repo.StudentRepository;
import com.example.spring_api_loadbalancer_nginx.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final StudentMapper studentMapper;

    /// add new student to database
    /// if email exists throw exception
    @CacheEvict(value = "student", allEntries = true)
    @Transactional
    @Override
    public StudentResponse addStudents(StudentRequest student) {
        if (existsStudentByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Student s = studentMapper.toEntity(student);
        Student saved = studentRepo.save(s);
        StudentResponse response = studentMapper.toDto(saved);

        return response;
    }

    /// get all students
    /// cacheable for redis
    @Cacheable(value = "student")
    @Override
    public List<StudentResponse> allStudents() {
        return studentRepo.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    /// find student by id
    /// cacheable for redis with key = id
    @Cacheable(value = "student", key = "#id")
    @Override
    public StudentResponse findOneStudent(Long id) {
        Student student = findStudentById(id);
        return studentMapper.toDto(student);
    }

    /// update student by id
    /// if email exists throw exception
    @CacheEvict(value = "student", key = "#id", allEntries = true)
    @Transactional
    @Override
    public StudentResponse updateStudent(StudentRequest student, Long id) {
        Student update = findStudentById(id);

        if (existsStudentByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        studentMapper.updateEntity(update, student);
        Student saved = studentRepo.save(update);

        return studentMapper.toDto(saved);
    }

    /// delete student by id
    /// cache evict all entries
    @CacheEvict(value = "student", allEntries = true)
    @Transactional
    @Override
    public String deleteStudent(Long id) {
        Student student = findStudentById(id);
        studentRepo.delete(student);
        return "Delete student id " + id + " success...";
    }

    /// This method is use for check exists student by email
    /// if it exists return @true, otherwise return @false
    boolean existsStudentByEmail(String email) {
        return studentRepo.existsByEmail(email);
    }

    /// This method is use for find student by id
    /// if it found it return @student data, otherwise return @exception error
    Student findStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("student not found!"));
    }
}