package com.example.hotelallocation.service;

import com.example.hotelallocation.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public Student getStudentFromId(Long id) {
        Student saran = new Student(id, "Saran", 1234);
        System.out.println("saran" + saran);
        return saran;
    }
}
