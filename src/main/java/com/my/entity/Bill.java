package com.my.entity;

public class Bill extends Entity{
    private Double sum;
    private Long userId;
    private Long roomId;
    BillStatus status;

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

    @Override
    public String toString() {
        return "Bill{" +
                "sum=" + sum +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", status=" + status +
                '}';
    }
}
