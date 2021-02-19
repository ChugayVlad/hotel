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
    <title><fmt:message key="register_jsp.title.registration"/></title>
</head>
<body>
<div class="container mx-auto my-xl-5" style="width: 300px;">
    <form name="registration" id="registration_form" action="controller" method="post" onsubmit="return validate()">
        <input type="hidden" name="command" value="register"/>
        <div class="mb-2">
            <input required type="text" name="email" id="inputEmail" class="form-control"
                   placeholder="<fmt:message key="authorization.email"/>"
                   pattern="^(([^<>()[\]\\.,;:\s@]+(\.[^<>()[\]\\.,;:\s@]+)*)|(.+))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$"
                   title="<fmt:message key="registration_jsp.invalidEmail"/> "
                   oninvalid="this.setCustomValidity(
                   <fmt:message key="registration_jsp.invalidEmail"/>)"
                   onchange="try{this.setCustomValidity('')}catch(e){}">
        </div>
        <div class="mb-2">

            <input required type="password" name="password" id="inputPassword" class="form-control" minlength="6"
                   maxlength="40" placeholder="<fmt:message key="authorization.password"/>"
                   title=
                   <fmt:message key="registration_jsp.invalidPassword"/> oninvalid="this.setCustomValidity(<fmt:message
            key="registration_jsp.invalidPassword"/>)
                   onchange="try{setCustomValidity('')}catch(e){}"/>
        </div>
        <div class="mb-2">

            <input required type="text" name="firstName" id="inputFirstName" class="form-control"
                   placeholder="<fmt:message key="authorization.first_name"/>" minlength="3"
                   maxlength="40"
                   title=
                   <fmt:message key="registration_jsp.invalidName"/>
                           oninvalid='this.setCustomValidity("<fmt:message key="registration_jsp.invalidName" />")'
                   onchange="try{setCustomValidity('')}catch(e){}">
        </div>
        <div class="mb-2">

            <input required type="text" id="inputLastName" name="lastName" class="form-control"
                   placeholder="<fmt:message key="authorization.last_name"/>" minlength="3" maxlength="40"
                   title=
                   <fmt:message key="registration_jsp.invalidName"/> required
                   oninvalid='this.setCustomValidity("<fmt:message key="registration_jsp.invalidName"/>")'
                   onchange="try{setCustomValidity('')}catch(e){}"/>
            <button class="btn btn-lg btn-primary btn-block my-3" style="align-content: center" type="submit">
                <fmt:message key="authorization.form_sign_up"/></button>
        </div>
    </form>
</div>
</body>
</html>
