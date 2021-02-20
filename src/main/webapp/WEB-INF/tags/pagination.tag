<%@ attribute name="path" required="true" %>

<%@ attribute name="page" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" %>
<%@ attribute name="maxPage" type="java.lang.Integer" required="true" %>
<%@ attribute name="sortBy" type="java.lang.String" required="false" %>
<%@ attribute name="status" type="java.lang.String" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div style="margin-left: 2%">
<c:choose>
    <c:when test="${page == 1}"><fmt:message key="pagination_tag.previous"/> </c:when>
    <c:otherwise>
        <a href="${path}&pageSize=${pageSize}&page=${page-1}&sortBy=${sortBy}&status=${status}"><fmt:message key="pagination_tag.previous"/></a>
    </c:otherwise>
</c:choose>
</div>
<div style="margin-left: 2%">
<c:choose>
    <c:when test="${page == maxPage}"><fmt:message key="pagination_tag.next"/></c:when>
    <c:otherwise>
        <a href="${path}&pageSize=${pageSize}&page=${page+1}&sortBy=${sortBy}&status=${status}"><fmt:message key="pagination_tag.next"/></a>
    </c:otherwise>
</c:choose>
</div>