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

    <fieldset>
        <legend>Date in</legend>
        <input required type="date" name="dateIn">
    </fieldset>

    <fieldset>
        <legend>Date out</legend>
        <input required type="date" name="dateOut">
    </fieldset>

    <fieldset>
        <legend>Places</legend>
        <select name="places">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
        </select>
    </fieldset>

    <fieldset>
        <legend>Room type</legend>
        <select name="roomType">
            <c:forEach var="type" items="${types}">
                <option value="${type.id}"><c:out value="${type.name}"/></option>
            </c:forEach>
        </select>
    </fieldset>
    <input type="submit" value="Make order">
</form>
</c:if>

<table id="rooms-list">

    <thead>
    <tr>
        <th scope="col">Type</th>
        <th scope="col">Places</th>
        <th scope="col">Price</th>
        <th scope="col">Status</th>
        <th scope="col">BUTTON</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="room" items="${rooms}">
        <tr>
            <td>"${room.type}</td>

            <td>${room.places}</td>
            <td>${room.price}</td>
            <td>${room.status}</td>
            <td>
                <a href="controller?command=roomDescription&roomId=${room.id}&roomStatus=${room.status}">Select room</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
