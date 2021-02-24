<%--
  Created by IntelliJ IDEA.
  User: vchuhai
  Date: 29.01.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%--<div class="order-wrapper bg-light">--%>

<c:if test="${user.roleId ne 0}">
    <section class="py-3 container">
        <div class="row py-lg-5">
            <form class="order-form" action="controller" method="post">
                <input type="hidden" name="command" value="makeOrder"/>

                <div class="order-form-item">
                    <label for="dateIn"><fmt:message key='main_jsp.form.date_in'/> </label>
                    <input class="form-select" id="dateIn" required type="date" name="dateIn">
                </div>
                <div class="order-form-item">
                    <label for="dateOut"><fmt:message key="main_jsp.form.date_out"/> </label>
                    <input class="form-select" required type="date" id="dateOut" name="dateOut">
                </div>
                <div class="order-form-item">
                    <label for="places"><fmt:message key="main_jsp.form.places"/></label>
                    <select class="form-select" id="places" name="places">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
                <div class="order-form-item">
                    <label for="type"><fmt:message key="main_jsp.form.room_type"/></label>
                    <select class="form-select" id="type" name="roomType">
                            <option value="STANDARD"><fmt:message key="STANDARD"/></option>
                            <option value="ECONOMY"><fmt:message key="ECONOMY"/></option>
                            <option value="DELUXE"><fmt:message key="DELUXE"/></option>
                            <option value="BUSINESS_DELUXE"><fmt:message key="BUSINESS_DELUXE"/></option>
                    </select>
                </div>
                <div class="order-submit">
                    <button type="submit" class="btn btn-primary"><fmt:message
                            key="main_jsp.form.submit_order"/></button>
                </div>
            </form>

        </div>
        <c:if test="${message ne null}">
            <div class="row alert alert-danger" role="alert">
                <fmt:message key="${message}"/>
            </div>
        </c:if>
    </section>
</c:if>


<div class="album py-5 bg-light">
    <div class="container">
        <form class="row g-3" id="sort" action="controller" method="get">

            <c:if test="${user.roleId == 0}">

                <input type="hidden" name="command" value="showRooms"/>
                <div class="col-auto">
                    <select class="form-select" name="status">
                        <option value="all"><fmt:message key="main_jsp.form.filter.all"/></option>
                        <option value="VACANT"><fmt:message key="main_jsp.form.filter.vacant"/></option>
                        <option value="BOOKED"><fmt:message key="main_jsp.form.filter.booked"/></option>
                        <option value="BUSY"><fmt:message key="main_jsp.form.filter.busy"/></option>
                        <option value="NOT_AVAILABLE"><fmt:message key="main_jsp.form.filter.not_available"/></option>
                    </select>
                </div>
            </c:if>

            <input type="hidden" name="command" value="showRooms"/>
            <div class="col-auto">
                <select class="form-select" name="sortBy">
                    <option value="type"><fmt:message key="main_jsp.form.sort.type"/></option>
                    <option value="places"><fmt:message key="main_jsp.form.sort.places"/></option>
                    <option value="price"><fmt:message key="main_jsp.form.sort.price"/></option>
                </select>
            </div>
            <div class="col-auto">
                <select class="form-select" name="order">
                    <option value="ASC"><fmt:message key="main_jsp.form.sort.ascending"/></option>
                    <option value="DESC"><fmt:message key="main_jsp.form.sort.descending"/></option>
                </select>
            </div>
            <input type="hidden" name="pageSize" value=${pageSize}>
            <input type="hidden" name="page" value=${page}>
            <div class="col-auto">
                <input type="submit" class="btn btn-primary"
                       value="<fmt:message key="main_jsp.form.sort.submit_sort"/>">
            </div>
        </form>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="room" items="${rooms}">
                <div class="card shadow-sm">
                    <img src="${pageContext.request.contextPath}/images/${room.image}" class="img-thumbnail"
                         style="height: 300px;">

                    <div class="card-body">
                        <p class="card-text" style="height: 250px">${room.description}</p>
                        <p class="card-text"><fmt:message key="main_jsp.table.places"/>: ${room.places}</p>
                        <p class="card-text"><fmt:message key="main_jsp.table.type"/>: <fmt:message key="${room.type}"/> </p>
                        <p class="card-text"><fmt:message key="main_jsp.table.price"/>: ${room.price}$</p>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-outline-secondary"><a
                                    href="controller?command=roomDescription&roomId=${room.id}&roomStatus=${room.status}"><fmt:message
                                    key="main_jsp.table.href"/></a></button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<div class="justify-center mb-5"><util:pagination path="controller?command=showRooms" page="${page}"
                                                  pageSize="${pageSize}" maxPage="${maxPage}"/></div>

<%--</div>--%>
</body>
</html>
