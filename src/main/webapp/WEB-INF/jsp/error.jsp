<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 29.01.2021
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>

<c:set var="title" value="Error" scope="page"/>
<body class="bg-light">

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">

            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
            <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

            <c:if test="${not empty code}">
                <h3>Error code: ${code}</h3>
            </c:if>

            <c:if test="${not empty message}">
                <h3>${message}</h3>
            </c:if>
            <div class="justify-center">
                <c:if test="${not empty errorMessage}">
                    <h3>${errorMessage}</h3>
                </c:if>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
