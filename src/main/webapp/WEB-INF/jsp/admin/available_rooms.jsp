<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 08.02.2021
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Find rooms for user</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
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
            <td>${room.type}</td>

            <td>${room.places}</td>
            <td>${room.price}</td>
            <td>${room.status}</td>
            <td>
                <a href="controller?command=setRoom&roomId=${room.id}&orderId=${orderId}">Set room</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
