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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Login</title>
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container mx-auto my-xl-5" style="width: 300px;">
    <form id="login-form" action="controller" method="post">

        <input type="hidden" name="command" value="login"/>
        <div class="justify-center">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login_jsp.form.welcome"/></h1>
        </div>
        <div class="mb-2">
            <input type="text" id="inputEmail" name="email" class="form-control"
                   placeholder="<fmt:message key="authorization.email"/> " required
                   autofocus>
        </div>
        <input type="password" name="password" id="inputPassword" class="form-control"
               placeholder="<fmt:message key="authorization.password"/> " required>
        <div class="justify-center">
            <button class="btn btn-lg btn-primary btn-block my-3" style="align-content: center" type="submit">
                <fmt:message
                        key="login_jsp.form.submit_sign_in"/></button>
            <a href="controller?command=register"><fmt:message key="authorization.form_sign_up"/> </a>
        </div>
    </form>

</div>
</body>
</html>
