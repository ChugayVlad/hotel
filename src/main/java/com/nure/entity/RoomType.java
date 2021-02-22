package com.nure.entity;

import java.util.Objects;

public class RoomType extends Entity{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(name, roomType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
