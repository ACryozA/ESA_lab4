package com.example.demo.model.mapper;

import com.example.demo.model.DanceTeacher;
import com.example.demo.model.dto.teacher.DanceTeacherRequest;
import com.example.demo.model.dto.teacher.DanceTeacherResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DanceTeacherMapper {
    DanceTeacherResponse toDto(DanceTeacher source);
    DanceTeacher toEntity(DanceTeacherResponse destination);
    DanceTeacher toEntity(DanceTeacherRequest destination);
}
