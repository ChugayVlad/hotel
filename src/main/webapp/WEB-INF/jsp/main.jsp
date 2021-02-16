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


<form id="sort" action="controller" method="get">

    <c:if test="${user.roleId == 0}">

        <input type="hidden" name="command" value="showRooms"/>

        <select name="status">
            <option value="all">All</option>
            <option value="VACANT">Vacant</option>
            <option value="BOOKED">Booked</option>
            <option value="BUSY">Busy</option>
            <option value="NOT_AVAILABLE">Not available</option>
        </select>

    </c:if>

    <input type="hidden" name="command" value="showRooms"/>

    <select name="sortBy">
        <option value="type_id">Sort by type</option>
        <option value="places">Sort by places</option>
        <option value="price">Sort by price</option>
    </select>

    <select name="order">
        <option value="ASC">Ascending</option>
        <option value="DESC">Descending</option>
    </select>

    <input type="hidden" name="pageSize" value=${pageSize}>
    <input type="hidden" name="page" value=${page}>
    <input type="submit" value="Sort">
</form>

<table id="rooms-list">
    <thead>
    <tr>
        <th scope="col">Type</th>
        <th scope="col">Places</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="room" items="${rooms}">
        <tr>
            <td>${room.type}</td>
            <td>${room.places}</td>
            <td>${room.price}</td>
            <td>
                <a href="controller?command=roomDescription&roomId=${room.id}&roomStatus=${room.status}">Select
                    room</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<util:pagination path="controller?command=showRooms" page="${page}" pageSize="${pageSize}" maxPage="${maxPage}"/>
</body>
</html>
