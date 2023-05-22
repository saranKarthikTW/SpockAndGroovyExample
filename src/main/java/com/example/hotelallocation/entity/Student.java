package com.example.hotelallocation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    private String name;

    @Column(name = "total monthly expense")
    private int totalMonthlyExpense;

    public Student(String name, int totalMonthlyExpense) {
        this.name = name;
        this.totalMonthlyExpense = totalMonthlyExpense;
    }
}
