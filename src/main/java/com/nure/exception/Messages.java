package com.nure.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {


    public static final String ERR_CANNOT_OBTAIN_ROOMS = "Can not obtain rooms";

    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Can not obtain a connection from the pool";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Can not obtain a user by its id";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_EMAIL = "User does not exist";

    public static final String ERR_CANNOT_UPDATE_USER = "Can not update a user";

    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Can not close a connection";

    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Can not close a result set";

    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Can not close a statement";

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Can not obtain the data source";

    public static final String ERR_CANNOT_OBTAIN_USERS = "Can not obtain users";
    public static final String ERR_CANNOT_OBTAIN_ROOM_TYPES = "Can not obtain room types";
    public static final String ERR_CANNOT_CREATE_ORDER = "Can not create order";
    public static final String ERR_CANNOT_OBTAIN_ORDERS = "Can not obtain orders";
    public static final String ERR_CANNOT_GET_ROOM_TYPE_BY_ID = "Can not get room type by id";
    public static final String ERR_CANNOT_CREATE_USER = "Can not create user";
    public static final String ERR_CANNOT_CREATE_BILL = "Can not create bill";

    public static String getMessageForLocale(String messageKey, String localeName) {
        Locale locale = new Locale(localeName);
        return ResourceBundle.getBundle("resources", locale)
                .getString(messageKey);
    }


    private Messages() {
        //no op
    }
}
