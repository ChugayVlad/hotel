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
<div class="container mx-xl-auto my-xl-5">
    <table id="orders-list" class="table">

        <thead class="bg-dark">
        <tr>
            <th scope="col">Places</th>
            <th scope="col">Date in</th>
            <th scope="col">Date out</th>
            <th scope="col">Type</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.places}</td>
                <td>${order.dateIn}</td>
                <td>${order.dateOut}</td>
                <td>${order.type.name}</td>
                <td>${order.user.firstName}</td>
                <td>${order.user.lastName}</td>
                <td>
                    <a href="controller?command=findRoomsForUser&orderId=${order.id}">Find rooms for user</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty message}">${message}</c:if>

    <util:pagination path="controller?command=showOrders" page="${page}" pageSize="${pageSize}" maxPage="${maxPage}"/>
</div>
</body>
</html>
