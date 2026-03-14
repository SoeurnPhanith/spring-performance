package com.example.spring_api_loadbalancer_nginx.repo;

import com.example.spring_api_loadbalancer_nginx.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByEmail(String email);

}
