package com.example.hostelallocation.entity;

import com.example.hostelallocation.entity.amenitytypes.OccupancyType;
import com.example.hostelallocation.entity.amenitytypes.PowerBackup;
import com.example.hostelallocation.entity.amenitytypes.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    @NotNull(message = "Occupancy Type cannot be null")
    private OccupancyType occupancyType;

    @NotNull(message = "Room Type cannot be null")
    private RoomType roomType;

    @NotNull(message = "Power backup type cannot be null")
    private PowerBackup powerBackup;

    @PositiveOrZero
    private int messSubscription;

    public Room(Long roomId, OccupancyType occupancyType, RoomType roomType, PowerBackup powerBackup) {
        this.roomId = roomId;
        this.occupancyType = occupancyType;
        this.roomType = roomType;
        this.powerBackup = powerBackup;
        this.messSubscription = 3000;
    }
}
