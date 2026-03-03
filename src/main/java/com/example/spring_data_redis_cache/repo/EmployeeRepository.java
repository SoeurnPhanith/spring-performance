package com.example.spring_data_redis_cache.repo;

import com.example.spring_data_redis_cache.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    boolean existsByEmployeeNo(String employeeNo);

    Optional<EmployeeEntity>findByEmployeeNo(String employeeNo);

}
