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

<a href="controller?command=openPersonalAccount&to=info">Personal information</a>
<br>
<a href="controller?command=openPersonalAccount&to=orders">Orders</a>
<br>
<a href="controller?command=openPersonalAccount&to=bills">Bills</a>
<br>

<c:choose>
    <c:when test="${to=='info'}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="editUser"/>
            Email
            <input type="text" name="email" value="${user.email}">
            First name
            <input type="text" name="firstName" value="${user.firstName}">
            Last name
            <input type="text" name="lastName" value="${user.lastName}">
            <input type="submit" value="Edit">
        </form>
    </c:when>
    <c:when test="${to=='orders'}">
        <hr>
        Orders
        <hr>

        <table id="orders-list">
            <thead>
            <tr>
                <th scope="col">Places</th>
                <th scope="col">Date in</th>
                <th scope="col">Date out</th>
                <th scope="col">Type</th>
                <th scope="col">User id</th>
                <th scope="col">Room id</th>
                <th scope="col">Sum to pay</th>
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
                    <td>${order.sum}</td>
                    <td>
                        <form id="book-form" action="controller" method="post">
                            <input type="hidden" name="command" value="bookRoom"/>
                            <input type="hidden" name="roomId" value="${order.roomId}">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <input type="hidden" name="sum" value="${order.sum}">
                            <input type="hidden" name="dateIn" value="${order.dateIn}">
                            <input type="hidden" name="dateOut" value="${order.dateOut}">
                            <c:if test="${order.roomId ne 0}">
                                <input type="submit" value="Accept">
                            </c:if>
                        </form>
                    </td>
                    <td>
                        <form id="cancel-form" action="controller" method="post">
                            <input type="hidden" name="command" value="cancelOrder"/>
                            <input type="hidden" name="orderId" value="${order.id}">
                            <input type="submit" value="Cancel">
                        </form>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:when test="${to == 'bills'}">
        <hr>
        Bills
        <hr>
        <table id="bills-list">
            <tbody>
            <c:forEach var="bill" items="${bills}">
                <form id="payment_form" action="controller" method="post">
                    <input type="hidden" name="command" value="pay"/>
                    <input type="hidden" name="sum" value="${bill.sum}">
                    <input type="hidden" name="billId" value="${bill.id}">
                    <tr>
                        <td>${bill.sum}</td>
                        <td>${bill.dateIn}</td>
                        <td>${bill.dateOut}</td>
                        <td>${bill.status}</td>
                        <c:if test="${bill.status == 'NOT_PAID'}">
                            <td><input type="text" name="money"></td>
                            <td><input type="submit" value="Pay"></td>
                        </c:if>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
</c:choose>
</body>
</html>
