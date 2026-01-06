package com.example.demo.controller;

import com.example.demo.model.DanceTeacher;
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
@RequestMapping("/teachers")
public class DanceTeacherController {
    @Autowired
    private DanceTeacherService teacherService;

    @GetMapping()
    public String home(Model model,
                       @RequestParam(required = false) String action,
                       @RequestParam(required = false) String id) {
        if ("delete".equals(action) && id != null && !id.isEmpty()) {
            long teacherId = Integer.parseInt(id);
            teacherService.delete(teacherId);
            return "redirect:/teachers";
        }

        if ("edit".equals(action) && id != null && !id.isEmpty()) {
            long teacherId = Integer.parseInt(id);
            DanceTeacher teacher = teacherService.getById(teacherId);
            model.addAttribute("editTeacher", teacher);
        }

        List<DanceTeacher> teachers = teacherService.getAll();
        model.addAttribute("danceTeacherList", teachers);
        return "danceTeacherList";
    }

    @PostMapping()
    public String saveOrUpdateTeacher(
            @RequestParam(required = false) Integer id,
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String phone) {

        DanceTeacher teacher;
        if (id != null) {
            teacher = teacherService.getById(id);
            if (teacher == null) {
                teacher = new DanceTeacher();
            }
        } else {
            teacher = new DanceTeacher();
        }

        teacher.setName(name);
        teacher.setAge(age);
        teacher.setPhone(phone);

        teacherService.save(teacher);

        return "redirect:/teachers";
    }
}
