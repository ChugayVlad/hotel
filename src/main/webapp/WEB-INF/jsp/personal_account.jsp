<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 08.02.2021
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Personal account</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<hr>
Bills
<hr>
<table id="bills-list">
    <tbody>
    <c:forEach var="bill" items="${bills}">
        <form id="payment_form" action="controller" method="post">
            <input type="hidden" name="command" value="pay"/>
            <tr>
                <td><c:out value="${bill.sum}"/></td>
                <td><c:out value="${bill.status}"/></td>
                <td><input type="number" name="pay"></td>
                <td><input type="submit" value="Pay"></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>

<hr>
Orders
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
            <td>${order.places}</td>
            <td>${order.dateIn}</td>
            <td>${order.dateOut}</td>
            <td>${order.type.name}</td>
            <td>${order.userId}</td>
            <td>${order.roomId}</td>
            <td>
                <form id="book-form" action="controller" method="post">
                    <input type="hidden" name="command" value="bookRoom"/>
                    <input type="hidden" name="roomId" value="${order.roomId}">
                    <input type="submit" value="Accept">
                </form>
            </td>
            <td>
                <form id="cancel-form" action="controller" method="post">
                    <input type="hidden" name="command" value="cancelOrder"/>
                    <input type="submit" value="Cancel">
                </form>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>
</body>
</html>
