<%@ attribute name="path" required="true" %>

<%@ attribute name="page" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" %>
<%@ attribute name="maxPage" type="java.lang.Integer" required="true" %>
<%@ attribute name="sortBy" type="java.lang.String" required="false" %>
<%@ attribute name="status" type="java.lang.String" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${page == 1}">Previous </c:when>
    <c:otherwise>
        <a href="${path}&pageSize=${pageSize}&page=${page-1}&sortBy=${sortBy}&status=${status}">Previous</a>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${page == maxPage}">Next</c:when>
    <c:otherwise>
        <a href="${path}&pageSize=${pageSize}&page=${page+1}&sortBy=${sortBy}&status=${status}">Next</a>
    </c:otherwise>
</c:choose>
