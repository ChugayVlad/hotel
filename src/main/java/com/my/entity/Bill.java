package com.my.entity;

import java.sql.Date;

public class Bill extends Entity{
    private Double sum;
    private Long userId;
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
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", status=" + status +
                '}';
    }
}
