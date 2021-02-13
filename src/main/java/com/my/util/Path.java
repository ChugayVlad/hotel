package com.my.util;

public final class Path {
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    public static final String PAGE_MAIN = "/WEB-INF/jsp/main.jsp";
    public static final String PAGE_ORDER_DONE = "/WEB-INF/jsp/order_done.jsp";
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/register.jsp";
    public static final String PAGE_ROOM_DESCRIPTION = "/WEB-INF/jsp/room.jsp";
    public static final String PAGE_PERSONAL_ACCOUNT = "/WEB-INF/jsp/personal_account.jsp";

    public static final String PAGE_ORDERS = "/WEB-INF/jsp/admin/orders.jsp";
    public static final String PAGE_AVAILABLE_ROOMS = "/WEB-INF/jsp/admin/available_rooms.jsp";

    public static final String COMMAND_ERROR_PAGE = "controller?command=error";
    public static final String COMMAND_SHOW_ROOMS = "controller?command=showRooms&sortBy=price&pageSize=4&page=1";
    public static final String COMMAND_OPEN_SUCCESS = "controller?command=successOrder";
    public static final String COMMAND_OPEN_PERSONAL_ACCOUNT = "controller?command=openPersonalAccount";
    public static final String COMMAND_LOGIN = "controller?command=login";
    public static final String COMMAND_SHOW_ORDERS = "controller?command=showOrders";
}
