package com.nure.entity;

public enum RoomStatus {
    VACANT, BOOKED, BUSY, NOT_AVAILABLE;

    public String getName() {
        return name().toLowerCase();
    }
}
