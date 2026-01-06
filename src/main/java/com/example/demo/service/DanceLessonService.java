package com.example.demo.service;

import com.example.demo.model.DanceLesson;
import com.example.demo.repository.DanceLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanceLessonService {
    @Autowired
    private DanceLessonRepository lessonRepository;

    public List<DanceLesson> getAll() {
        return lessonRepository.findAll();
    }

    public void save(DanceLesson newLesson) {
        lessonRepository.save(newLesson);
    }

    public DanceLesson getById(long id) {
        return lessonRepository.findById(id).get();
    }

    public void delete(long id) {
        lessonRepository.deleteById(id);
    }
}
