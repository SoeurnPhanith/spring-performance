package com.example.demo_spring_boot_docker.repo;

import com.example.demo_spring_boot_docker.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {
}
