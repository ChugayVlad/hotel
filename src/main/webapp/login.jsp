<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 27.01.2021
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<table id="main-container">
    <tr>
        <td class="content center">
            <form id="login_form" action="controller" method="post">

                <input type="hidden" name="command" value="login"/>

                <fieldset >
                    <legend>Email</legend>
                    <input required name="email"/><br/>
                </fieldset><br/>
                <fieldset>
                    <legend>Password</legend>
                    <input required type="password" name="password"/>
                </fieldset><br/>
                <input type="submit" value="Login">
            </form>

            <a href="controller?command=register">Sign up</a>
        </td>
    </tr>
</table>
</body>
</html>
