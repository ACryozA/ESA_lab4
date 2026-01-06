package com.example.demo.model.dto.lesson;

import com.example.demo.model.dto.teacher.DanceTeacherResponse;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "lesson")
public class DanceLessonResponse {
    private long id;

    private String style;

    private String level;

    private String schedule;

    private DanceTeacherResponse teacher;
}
