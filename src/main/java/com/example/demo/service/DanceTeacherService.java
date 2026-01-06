package com.example.demo.service;

import com.example.demo.model.DanceTeacher;
import com.example.demo.repository.DanceTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanceTeacherService {
    @Autowired
    private DanceTeacherRepository teacherRepository;

    public DanceTeacher findById(long id) {
        return teacherRepository.findById(id).get();
    }

    public List<DanceTeacher> getAll() {
        return teacherRepository.findAll();
    }

    public void save(DanceTeacher newTeacher) {
        teacherRepository.save(newTeacher);
    }

    public DanceTeacher getById(long id) {
        return teacherRepository.findById(id).get();
    }

    public void delete(long id) {
        teacherRepository.deleteById(id);
    }
}
