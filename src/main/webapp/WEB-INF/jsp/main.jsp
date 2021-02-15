<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 29.01.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:if test="${user.roleId ne 0}">
    <form id="order" action="controller" method="post">
        <input type="hidden" name="command" value="makeOrder"/>
            Date in
            <input required type="date" name="dateIn">

            Date out
            <input required type="date" name="dateOut">

            Places
            <select name="places">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
            </select>

            Room type
            <select name="roomType">
                <c:forEach var="type" items="${types}">
                    <option value="${type.id}"><c:out value="${type.name}"/></option>
                </c:forEach>
            </select> <br>
        <input type="submit" value="Make order">
    </form>
</c:if>

<c:if test="${user.roleId == 0}">

        <a href="controller?command=showRooms&sortBy=${sortBy}&pageSize=${pageSize}&page=${page}&status=VACANT">Vacant</a>
        <a href="controller?command=showRooms&sortBy=${sortBy}&pageSize=${pageSize}&page=${page}&status=BOOKED">Booked</a>
        <a href="controller?command=showRooms&sortBy=${sortBy}&pageSize=${pageSize}&page=${page}&status=BUSY">Busy</a>
        <a href="controller?command=showRooms&sortBy=${sortBy}&pageSize=${pageSize}&page=${page}&status=NOT_AVAILABLE">Not available</a>
</c:if>

<table id="rooms-list">

    <thead>
    <tr>
        <th scope="col"><a href="controller?command=showRooms&sortBy=type_id&pageSize=${pageSize}&page=${page}">Type</a></th>
        <th scope="col"><a href="controller?command=showRooms&sortBy=places&pageSize=${pageSize}&page=${page}">Places</a></th>
        <th scope="col"><a href="controller?command=showRooms&sortBy=price&pageSize=${pageSize}&page=${page}">Price</a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="room" items="${rooms}">
        <tr>
            <td>${room.type}</td>

            <td>${room.places}</td>
            <td>${room.price}</td>
            <td>
                <a href="controller?command=roomDescription&roomId=${room.id}&roomStatus=${room.status}">Select room</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<util:pagination path="controller?command=showRooms" page="${page}" pageSize="${pageSize}" maxPage="${maxPage}"/>
</body>
</html>
