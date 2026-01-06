package com.example.demo.controller;

import com.example.demo.model.DanceLesson;
import com.example.demo.model.DanceTeacher;
import com.example.demo.service.DanceLessonService;
import com.example.demo.service.DanceTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/lessons")
public class DanceLessonController {
    @Autowired
    private DanceLessonService lessonService;
    @Autowired
    private DanceTeacherService teacherService;

    @GetMapping()
    public String home(Model model,
                       @RequestParam(required = false) String action,
                       @RequestParam(required = false) String id) {

        if ("delete".equals(action) && id != null && !id.isEmpty()) {
            int lessonId = Integer.parseInt(id);
            lessonService.delete(lessonId);
            return "redirect:/lessons";
        }

        if ("edit".equals(action) && id != null && !id.isEmpty()) {
            int lessonId = Integer.parseInt(id);
            DanceLesson lesson = lessonService.getById(lessonId);
            model.addAttribute("editLesson", lesson);
        }

        List<DanceLesson> lessons = lessonService.getAll();
        List<DanceTeacher> teachers = teacherService.getAll();
        model.addAttribute("danceLessonList", lessons);
        model.addAttribute("teachers", teachers);
        return "danceLessonList";
    }

    @PostMapping()
    public String createLesson(
            @RequestParam String style,
            @RequestParam String level,
            @RequestParam String schedule,
            @RequestParam long teacherId,
            @RequestParam(required = false) Integer id) {

        DanceLesson lesson;
        if (id != null) {
            lesson = lessonService.getById(id);
            if (lesson == null) {
                lesson = new DanceLesson();
            }
        } else {
            lesson = new DanceLesson();
        }

        lesson.setStyle(style);
        lesson.setLevel(level);
        lesson.setSchedule(schedule);
        lesson.setTeacher(teacherService.findById(teacherId));

        lessonService.save(lesson);
        return "redirect:/lessons";
    }
}
