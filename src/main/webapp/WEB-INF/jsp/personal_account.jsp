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
    <title><fmt:message key="personal_account_jsp.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="d-flex h-100 wrapper" id="wrapper">
    <div class="bg-light border-right h-100 aside" id="sidebar-wrapper">
        <div class="list-group list-group-flush">
            <a href="controller?command=openPersonalAccount&to=orders"
               class="list-group-item list-group-item-action bg-light"><fmt:message
                    key="personal_account_jsp.href.orders"/></a>

            <a href="controller?command=openPersonalAccount&to=bills"
               class="list-group-item list-group-item-action bg-light"><fmt:message
                    key="personal_account_jsp.href.bills"/></a>

            <a href="controller?command=openPersonalAccount&to=info"
               class="list-group-item list-group-item-action bg-light"><fmt:message
                    key="personal_account_jsp.href.personal_information"/></a>
        </div>
    </div>

    <div class="container-fluid p-3 justify-center main">
        <c:choose>
            <c:when test="${to=='orders'}">
                <table id="orders-list" class="table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="main_jsp.table.places"/></th>
                        <th scope="col"><fmt:message key="main_jsp.form.date_in"/></th>
                        <th scope="col"><fmt:message key="main_jsp.form.date_out"/></th>
                        <th scope="col"><fmt:message key="main_jsp.table.type"/></th>
                        <th scope="col"><fmt:message key="personal_account_jsp.table.sum"/></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.places}</td>
                            <td>${order.dateIn}</td>
                            <td>${order.dateOut}</td>
                            <td>${order.type.name}</td>
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
                                        <input class="btn btn-dark" type="submit"
                                               value="<fmt:message key="personal_account_jsp.order.accept"/>">
                                    </c:if>
                                </form>
                            </td>
                            <td>
                                <form id="cancel-form" action="controller" method="post">
                                    <input type="hidden" name="command" value="cancelOrder"/>
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input class="btn btn-close" type="submit">
                                </form>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${to == 'bills'}">

                <table id="bills-list" class="table">
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
                                    <td><input class="btn btn-outline-primary" type="submit" value="<fmt:message key="personal_account_jsp.bill.pay"/>">
                                    </td>
                                </c:if>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${to=='info'}">
                <form action="controller" method="post"
                      class="d-flex flex-column justify-content-around align-items-center form"
                >

                    <input type="hidden" name="command" value="editUser"/>
                    <div class="form-item">
                        <fmt:message key="authorization.email"/>
                        <input type="text" name="email" value="${user.email}">
                    </div>
                    <div class="form-item">
                        <fmt:message key="authorization.first_name"/>
                        <input type="text" name="firstName" value="${user.firstName}">
                    </div>
                    <div class="form-item">
                        <fmt:message key="authorization.last_name"/>
                        <input type="text" name="lastName" value="${user.lastName}">
                    </div>
                    <div>
                        <input type="submit" class="btn btn-primary" style="width: 150px;"
                               value="<fmt:message key="personal_account_jsp.form.submit_edit"/>">
                    </div>
                </form>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>
