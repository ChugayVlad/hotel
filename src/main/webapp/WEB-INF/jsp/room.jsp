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
ROOM DESCRIPTION
<form id="room_description" action="controller" method="post">
    <c:out value="${roomId}"/>
    <input type="hidden" name="command" value="bookRoom"/>
    <input type="hidden" name="roomId" value="${roomId}">
    <input type="submit" value="Book">
</form>
</body>
</html>
