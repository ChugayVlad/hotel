<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 03.02.2021
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form id="registration_form" action="controller" method="post">
    <input type="hidden" name="command" value="register"/>
    Email
    <input type="email" name="email" /><br/>
    Password
    <input type="password" name="password"  /><br/>
    First name
    <input type="text" name="firstName" /><br/>
    Last name
    <input type="text" name="lastName" /><br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>
