<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 08.02.2021
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<table id="orders-list">

    <thead>
    <tr>
        <th scope="col">Places</th>
        <th scope="col">Date in</th>
        <th scope="col">Date out</th>
        <th scope="col">Type</th>
        <th scope="col">User id</th>
        <th scope="col">Room id</th>
        <th scope="col">BUTTON</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td><c:out value="${order.places}"/></td>
            <td><c:out value="${order.dateIn}"/></td>
            <td><c:out value="${order.dateOut}"/></td>
            <td><c:out value="${order.type.name}"/></td>
            <td><c:out value="${order.userId}"/></td>
            <td><c:out value="${order.roomId}"/></td>
            <td>
                <a href="controller?command=findRoomsForUser&orderId=${order.id}">Find rooms for user</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${not empty message}">${message}</c:if>
</body>
</html>
