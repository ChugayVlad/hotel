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
<div class="container mx-xl-auto my-xl-5">
    <table id="rooms-list" class="table">

        <thead class="thead-dark">
        <tr>
            <th scope="col"><fmt:message key="main_jsp.table.type"/></th>
            <th scope="col"><fmt:message key="main_jsp.table.places"/></th>
            <th scope="col"><fmt:message key="main_jsp.table.price"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${rooms}">
            <tr>
                <td>${room.type}</td>
                <td>${room.places}</td>
                <td>${room.price}$</td>
                <td>
                    <a href="controller?command=setRoom&roomId=${room.id}&orderId=${orderId}"><fmt:message
                            key="available_rooms_jsp.set_room"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
