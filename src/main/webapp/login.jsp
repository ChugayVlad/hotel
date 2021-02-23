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
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container mx-auto my-xl-5" style="width: 300px;">
    <form id="login-form" action="controller" method="post">

        <input type="hidden" name="command" value="login"/>

        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

        <div class="mb-2">
        <input type="text" id="inputEmail" name="email" class="form-control" placeholder="<fmt:message key="authorization.email"/> " required
               autofocus>
        </div>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="authorization.password"/> " required>

        <button class="btn btn-lg btn-primary btn-block my-3" style="align-content: center" type="submit"><fmt:message key="login_jsp.form.submit_sign_in"/> </button>
        <a href="controller?command=register"><fmt:message key="authorization.form_sign_up"/> </a>
    </form>

</div>
</body>
</html>
