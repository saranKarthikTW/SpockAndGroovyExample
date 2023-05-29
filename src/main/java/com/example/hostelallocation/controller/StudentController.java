package com.example.hostelallocation.controller;

import com.example.hostelallocation.entity.Student;
import com.example.hostelallocation.service.StudentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@NotNull @PathVariable Long studentId) throws Exception {
        return studentService.getStudentFromId(studentId);
    }
}
