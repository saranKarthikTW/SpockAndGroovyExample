package com.example.hostelallocation.service;

import com.example.hostelallocation.entity.Room;
import com.example.hostelallocation.exception.InvalidRoomIdException;
import com.example.hostelallocation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Integer getExpenseForRoomId(Long roomId) throws InvalidRoomIdException {
        Room room = getRoomFromId(roomId);
        int occupancyCost = room.getOccupancyType().cost;
        int roomCost = room.getRoomType().cost;
        int powerBackupCost = room.getPowerBackup().cost;
        int messSubscriptionCost = room.getMessSubscription();

        return occupancyCost + roomCost + powerBackupCost + messSubscriptionCost;
    }

    private Room getRoomFromId(Long roomId) throws InvalidRoomIdException {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new InvalidRoomIdException("No room exists for that ID");
        }
        return optionalRoom.get();
    }
}
