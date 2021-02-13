<%@ attribute name="path" required="true" %>

<%@ attribute name="page" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" %>
<%@ attribute name="maxPage" type="java.lang.Integer" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${page == 1}">Previous </c:when>
    <c:otherwise>
        <a href="${path}&pageSize=${pageSize}&page=${page-1}&sortBy=${sortBy}">Previous</a>
    </c:otherwise>
</c:choose>


<c:choose>
    <c:when test="${page == maxPage}">Next</c:when>
    <c:otherwise>
        <a href="${path}&pageSize=${pageSize}&page=${page+1}&sortBy=${sortBy}">Next</a>
    </c:otherwise>
</c:choose>
