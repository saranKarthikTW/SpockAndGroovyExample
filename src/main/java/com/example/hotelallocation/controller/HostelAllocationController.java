package com.example.hotelallocation.controller;

import com.example.hotelallocation.service.HostelAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostelAllocationController {
    private final HostelAllocationService hostelAllocationService;

    @Autowired
    public HostelAllocationController(HostelAllocationService hostelAllocationService) {
        this.hostelAllocationService = hostelAllocationService;
    }

    @PostMapping("/updateExpense/{studentId}")
    public void updateExpenseForStudent(@PathVariable("studentId") long studentId) throws Exception {
        hostelAllocationService.updateExpensesForStudentId(studentId);
    }

}
