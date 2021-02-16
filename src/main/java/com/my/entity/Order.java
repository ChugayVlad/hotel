package com.my.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Order extends Entity {
    private Integer places;
    private Date dateIn;
    private Date dateOut;
    private RoomType type;

    private User user;

    private Long roomId;
    private Double sum;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "places=" + places +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", type=" + type +
                ", user=" + user +
                ", roomId=" + roomId +
                ", sum=" + sum +
                '}';
    }
}
