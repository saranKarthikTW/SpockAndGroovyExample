package com.example.hotelallocation.service;

import com.example.hotelallocation.entity.Hostel;
import com.example.hotelallocation.exception.InvalidStudentIdException;
import com.example.hotelallocation.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostelService {
    private final HostelRepository hostelRepository;

    @Autowired
    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public Long getRoomIdForStudentId(Long studentId) throws InvalidStudentIdException {
        Optional<Hostel> optionalHostelOfStudent = hostelRepository.findByStudentId(studentId);
        if (optionalHostelOfStudent.isEmpty()) {
            throw new InvalidStudentIdException("Student ID not found");
        }
        return optionalHostelOfStudent.get().getRoomId();
    }

}
