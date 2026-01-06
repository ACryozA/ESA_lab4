package com.example.demo.controller.rest;

import com.example.demo.model.DanceTeacher;
import com.example.demo.model.dto.teacher.DanceTeacherRequest;
import com.example.demo.model.mapper.DanceLessonMapper;
import com.example.demo.model.mapper.DanceTeacherMapper;
import com.example.demo.service.DanceTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/teachers")
public class DanceTeacherRestController extends AbstractRestController{
    @Autowired
    private DanceTeacherService teacherService;
    @Autowired
    DanceTeacherMapper danceTeacherMapper;
    @Autowired
    DanceLessonMapper danceLessonMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> getAll(@RequestHeader HttpHeaders headers) {
        return prepareResponse(teacherService.getAll().stream().map(danceTeacherMapper::toDto).toList(), XSL_TEACHERS_STYLESHEET);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        DanceTeacher teacher = teacherService.findById(id);

        return prepareResponse(danceTeacherMapper.toDto(teacher), XSL_TEACHER_STYLESHEET);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Void> create(@RequestBody DanceTeacherRequest teacher) {
        teacherService.save(danceTeacherMapper.toEntity(teacher));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody DanceTeacherRequest teacher) {
        DanceTeacher updatedTeacher = teacherService.findById(id);
        updatedTeacher.setName(teacher.getName());
        updatedTeacher.setAge(teacher.getAge());
        updatedTeacher.setPhone(teacher.getPhone());

        teacherService.save(updatedTeacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/lessons",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> getLessons(@PathVariable Long id) {
        DanceTeacher teacher = teacherService.findById(id);
        return prepareResponse(teacher.getLessons().stream().map(danceLessonMapper::toDto).toList(), XSL_LESSONS_STYLESHEET);
    }
}
