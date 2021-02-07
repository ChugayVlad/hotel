package com.my.entity;


public class Room extends Entity {
    private Integer places;
    private Double price;
    private RoomStatus status;
    private RoomType type;

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "places=" + places +
                ", price=" + price +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
