package com.example.hotelallocation.service;

import com.example.hotelallocation.entity.Student;
import com.example.hotelallocation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentFromId(Long id) throws Exception {
        Optional<Student> studentFromRepo = studentRepository.findById(id);
        if (studentFromRepo.isEmpty()) {
            throw new Exception("Invalid User id");
        }
        return studentFromRepo.get();
    }
}
