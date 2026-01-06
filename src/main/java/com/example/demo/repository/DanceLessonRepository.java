package com.example.demo.repository;

import com.example.demo.model.DanceLesson;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanceLessonRepository extends JpaRepository<DanceLesson, Long> {
    @Override
    @EntityGraph(attributePaths = {"teacher"})
    List<DanceLesson> findAll();
}