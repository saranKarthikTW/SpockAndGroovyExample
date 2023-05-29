package com.example.hotelallocation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Hostel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hostelId;

    @NotNull(message = "Room id should not be empty")
    private Long roomId;

    @NotNull(message = "Student id should not be empty")
    private Long studentId;

    public Hostel(Long roomId, Long studentId) {
        this.roomId = roomId;
        this.studentId = studentId;
    }
}
