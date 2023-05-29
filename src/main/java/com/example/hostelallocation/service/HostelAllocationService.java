package com.example.hostelallocation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostelAllocationService {
    private final HostelService hostelService;
    private final StudentService studentService;
    private final RoomService roomService;

    @Autowired
    public HostelAllocationService(HostelService hostelService, StudentService studentService, RoomService roomService) {
        this.hostelService = hostelService;
        this.studentService = studentService;
        this.roomService = roomService;
    }

    public void updateExpensesForStudentId(Long studentId) throws Exception {
        Long roomIdOfStudent = hostelService.getRoomIdForStudentId(studentId);
        Integer roomExpense = 0;
        if (roomIdOfStudent != null) {
            roomExpense = roomService.getExpenseForRoomId(roomIdOfStudent);
        }
        studentService.updateExpenseForStudentId(studentId, roomExpense);
    }
}
