package com.nure.entity;

import java.sql.Date;
import java.util.Objects;

public class Bill extends Entity{
    private Double sum;
    private User user;
    private Long roomId;
    private Date dateIn;
    private Date dateOut;
    private BillStatus status;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
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

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Bill{" +
                "sum=" + sum +
                ", user=" + user +
                ", roomId=" + roomId +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(sum, bill.sum) &&
                Objects.equals(user, bill.user) &&
                Objects.equals(roomId, bill.roomId) &&
                Objects.equals(dateIn, bill.dateIn) &&
                Objects.equals(dateOut, bill.dateOut) &&
                status == bill.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, user, roomId, dateIn, dateOut, status);
    }
}
