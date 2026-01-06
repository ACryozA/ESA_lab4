package com.example.demo.controller.rest;

import com.example.demo.model.DanceLesson;
import com.example.demo.model.dto.lesson.DanceLessonRequest;
import com.example.demo.model.mapper.DanceLessonMapper;
import com.example.demo.service.DanceLessonService;
import com.example.demo.service.DanceTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/lessons")
public class DanceLessonRestController extends AbstractRestController{
    @Autowired
    private DanceLessonService lessonService;
    @Autowired
    private DanceTeacherService teacherService;
    @Autowired
    DanceLessonMapper danceLessonMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> getAll() {
        return prepareResponse(lessonService.getAll().stream().map(danceLessonMapper::toDto).toList(), XSL_LESSONS_STYLESHEET);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        DanceLesson teacher = lessonService.getById(id);
        return prepareResponse(danceLessonMapper.toDto(teacher), XSL_LESSON_STYLESHEET);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Void> create(@RequestBody DanceLessonRequest lesson) {
        DanceLesson newLesson = danceLessonMapper.toEntity(lesson);
        newLesson.setTeacher(teacherService.findById(lesson.getTeacherId()));
        lessonService.save(newLesson);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody DanceLessonRequest lesson) {
        DanceLesson updatedLesson = lessonService.getById(id);
        updatedLesson.setStyle(lesson.getStyle());
        updatedLesson.setLevel(lesson.getLevel());
        updatedLesson.setSchedule(lesson.getSchedule());
        updatedLesson.setTeacher(teacherService.findById(lesson.getTeacherId()));

        lessonService.save(updatedLesson);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
