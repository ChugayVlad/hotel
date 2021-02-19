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
    <title>Room</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:choose>
    <c:when test="${userRole.name == 'admin' }">
        <table>
            <tbody>
            <c:forEach var="bill" items="${bills}">
                <tr>
                    <td>${bill.user.firstName}</td>
                    <td>${bill.user.lastName}</td>
                    <td>${bill.dateIn}</td>
                    <td>${bill.dateOut}</td>
                    <td>${bill.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>

    <c:otherwise>
        ${roomDesc}

        <img src="${pageContext.request.contextPath}/images/1.jpg"/>

        <form id="room_description" action="controller" method="post">
            <input type="hidden" name="command" value="bookRoom"/>
            <input type="hidden" name="roomId" value="${roomId}">
            <fmt:message key="main_jsp.form.date_in"/>
            <input required type="date" name="dateIn">
            <fmt:message key="main_jsp.form.date_out"/>
            <input required type="date" name="dateOut">
            <input type="submit" value="<fmt:message key="room_jsp.form_submit_book"/>">
        </form>
    </c:otherwise>
</c:choose>
${message}
</body>
</html>
