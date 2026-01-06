package com.example.demo.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class AbstractRestController {

    protected static final String XSL_LESSONS_STYLESHEET = "<?xml-stylesheet type=\"text/xsl\" href=\"/xslt/lessons.xsl\"?>";
    protected static final String XSL_LESSON_STYLESHEET = "<?xml-stylesheet type=\"text/xsl\" href=\"/xslt/lesson.xsl\"?>";
    protected static final String XSL_TEACHERS_STYLESHEET = "<?xml-stylesheet type=\"text/xsl\" href=\"/xslt/teachers.xsl\"?>";
    protected static final String XSL_TEACHER_STYLESHEET = "<?xml-stylesheet type=\"text/xsl\" href=\"/xslt/teacher.xsl\"?>";

    protected ResponseEntity<Object> prepareResponse(Object object, String XslStylesheet) {
        if (isXmlRequest()) {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_XML)
                        .body(XslStylesheet + xmlMapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                JsonMapper jsonMapper = new JsonMapper();
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(jsonMapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isXmlRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String acceptHeader = request.getHeader("Accept");
        return acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE);
    }
}
