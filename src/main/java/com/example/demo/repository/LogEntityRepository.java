package com.example.demo.repository;

import com.example.demo.model.jms.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Long> {
}

