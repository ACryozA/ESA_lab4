package com.example.demo.model.dto.lesson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "lesson")
public class DanceLessonRequest {
    private String style;

    private String level;

    private String schedule;

    private long teacherId;
}
