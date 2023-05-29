package com.example.hotelallocation.entity.amenitytypes;

public enum OccupancyType {
    SINGLE(2000),
    DOUBLE(1000),
    TRIPLE(500);

    public final int cost;

    OccupancyType(int cost) {
        this.cost = cost;
    }
}