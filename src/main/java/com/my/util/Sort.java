package com.my.util;

import com.my.entity.Room;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    public static void sort(String method, List<Room> list){
        if ("sortType".equals(method)){
            list.sort(Comparator.comparing(r -> r.getType().getName()));
        }
        if ("sortPlaces".equals(method)){
            list.sort(Comparator.comparing(Room::getPlaces));
        }
        if ("sortPrice".equals(method)){
            list.sort(Comparator.comparing(Room::getPrice));
        }
    }
}
