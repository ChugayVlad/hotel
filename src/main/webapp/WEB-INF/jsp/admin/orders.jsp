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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Orders</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container mx-xl-auto my-xl-5">
    <table id="orders-list" class="table table-hover orders-table">

        <thead class="table-dark">
        <tr>
            <th scope="col"><fmt:message key="main_jsp.table.places"/></th>
            <th scope="col"><fmt:message key="main_jsp.form.date_in"/></th>
            <th scope="col"><fmt:message key="main_jsp.form.date_out"/></th>
            <th scope="col"><fmt:message key="main_jsp.table.type"/></th>
            <th scope="col"><fmt:message key="authorization.first_name"/></th>
            <th scope="col"><fmt:message key="authorization.last_name"/></th>
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
                    <a href="controller?command=findRoomsForUser&orderId=${order.id}"><fmt:message
                            key="orders_jsp.table.href.find_rooms"/> </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty message}">${message}</c:if>

    <div class="justify-center mb-5">
        <util:pagination path="controller?command=showOrders" page="${page}" pageSize="${pageSize}"
                         maxPage="${maxPage}"/>
    </div>
</div>
</body>
</html>
