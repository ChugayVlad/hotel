package com.my.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Order extends Entity {
    private Integer places;
    private Date dateIn;
    private Date dateOut;
    private RoomType type;
    private Long userId;
    private Long roomId;

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "places=" + places +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", type=" + type +
                ", userId=" + userId +
                ", roomId=" + roomId +
                '}';
    }
}
