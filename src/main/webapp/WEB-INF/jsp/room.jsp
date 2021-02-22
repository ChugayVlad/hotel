<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 05.02.2021
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Room</title>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:choose>
    <c:when test="${userRole.name == 'admin' }">
        <div class="container mx-xl-auto my-xl-5">
            <table class="table">
                <thead class="table-dark">
                <tr>
                    <th scope="col"><fmt:message key="authorization.first_name"/></th>
                    <th scope="col"><fmt:message key="authorization.last_name"/></th>
                    <th scope="col"><fmt:message key="main_jsp.form.date_in"/></th>
                    <th scope="col"><fmt:message key="main_jsp.form.date_out"/></th>
                    <th scope="col"><fmt:message key="room_jsp.payment_status"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bill" items="${bills}">
                    <tr>
                        <td>${bill.user.firstName}</td>
                        <td>${bill.user.lastName}</td>
                        <td><fmt:formatDate value="${bill.dateIn}"/></td>
                        <td><fmt:formatDate value="${bill.dateOut}"/></td>
                        <c:if test="${bill.status == 'PAID'}">
                            <td><fmt:message key="room_jsp.payment_status.paid"/></td>
                        </c:if>
                        <c:if test="${bill.status == 'NOT_PAID'}">
                            <td><fmt:message key="room_jsp.payment_status.not_paid"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>

    <c:otherwise>
        <div class="d-flex justify-content-center pt-5">
            <form class="row g-3" id="room_description" action="controller" method="post">
                <input type="hidden" name="command" value="bookRoom"/>

                <input type="hidden" name="roomId" value="${roomId}">
                <div class="col-auto">
                    <p class="form-text"><fmt:message key="main_jsp.form.date_in"/></p>
                    <input class="form-select" required type="date" name="dateIn">
                </div>
                <div class="col-auto">
                    <p class="form-text"><fmt:message key="main_jsp.form.date_out"/></p>
                    <input class="form-select" required type="date" name="dateOut">
                </div>
                <div class="col-auto">
                    <p class="form-text invisible">Label</p>
                    <input class="btn btn-primary btn-sm" type="submit"
                           value="<fmt:message key="room_jsp.form_submit_book"/>">
                </div>
                <c:if test="${message ne null}">
                    <div class="row mt-3 alert alert-danger" role="alert">
                       <fmt:message key="${message}"/>
                    </div>
                </c:if>
            </form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
