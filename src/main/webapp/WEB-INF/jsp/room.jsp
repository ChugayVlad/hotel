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
${roomDesc}
<form id="room_description" action="controller" method="post">
    <input type="hidden" name="command" value="bookRoom"/>
    <input type="hidden" name="roomId" value="${roomId}">
    Date in
    <input required type="date" name="dateIn">
    Date out
    <input required type="date" name="dateOut">
    <input type="submit" value="Book">
</form>
</body>
</html>
