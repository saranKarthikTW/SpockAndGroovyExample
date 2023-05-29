package com.example.hotelallocation.entity.amenitytypes;

public enum RoomType {
    AC(1500),
    NON_AC(0);

    public final int cost;

    RoomType(int cost) {
        this.cost = cost;
    }
}
