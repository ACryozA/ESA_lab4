package com.example.demo.model.mapper;

import com.example.demo.model.DanceLesson;
import com.example.demo.model.dto.lesson.DanceLessonRequest;
import com.example.demo.model.dto.lesson.DanceLessonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DanceTeacherMapper.class)
public interface DanceLessonMapper {
    DanceLessonResponse toDto(DanceLesson source);
    DanceLesson toEntity(DanceLessonRequest destination);
}
