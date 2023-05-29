package com.example.hostelallocation.service;

import com.example.hostelallocation.entity.Student;
import com.example.hostelallocation.exception.InvalidStudentIdException;
import com.example.hostelallocation.repository.StudentRepository;
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
            throw new InvalidStudentIdException("No student exists for that ID");
        }
        return studentFromRepo.get();
    }

    public void updateExpenseForStudentId(Long studentId, Integer roomExpense) throws Exception {
        Student student = getStudentFromId(studentId);
        student.setTotalMonthlyExpense(roomExpense);
        studentRepository.save(student);
    }
}
