package com.example.demo.repository;

import com.example.demo.model.DanceTeacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceTeacherRepository extends JpaRepository<DanceTeacher, Long> {
}