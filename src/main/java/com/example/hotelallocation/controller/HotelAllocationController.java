package com.example.hotelallocation.controller;

import com.example.hotelallocation.entity.Student;
import com.example.hotelallocation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelAllocationController {
    private final StudentService studentService;

    @Autowired
    public HotelAllocationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam Long id) throws Exception {
        return studentService.getStudentFromId(id);
    }
}
