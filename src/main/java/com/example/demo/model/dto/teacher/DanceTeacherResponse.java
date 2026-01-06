package com.example.demo.model.dto.teacher;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "teacher")
public class DanceTeacherResponse {
    private long id;

    private String name;

    private int age;

    private String phone;
}
