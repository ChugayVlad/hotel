package com.my.entity;

import java.time.LocalDate;

public class Order extends Entity {
    private Integer places;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private RoomType type;
    private User user;
    private Room room;

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Order{" +
                "places=" + places +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", type=" + type +
                ", user=" + user +
                ", room=" + room +
                '}';
    }
}
