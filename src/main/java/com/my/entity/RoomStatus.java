package com.my.entity;

public enum RoomStatus {
    VACANT, BOOKED, BUSY, NOT_AVAILABLE;

    public String getName() {
        return name().toLowerCase();
    }
}
