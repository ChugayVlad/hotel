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
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container mx-xl-auto my-xl-5">

    <c:if test="${user.roleId ne 0}">
        <form class="form-inline" action="controller" method="post">
            <input type="hidden" name="command" value="makeOrder"/>

            <label class="form-inline my-1 mr-2" for="dateIn"><fmt:message key='main_jsp.form.date_in'/> </label>
            <input class="form-inline" id="dateIn" required type="date" name="dateIn">

            <label class="form-inline my-1 mr-2" for="dateOut"><fmt:message key="main_jsp.form.date_out"/> </label>
            <input required class="form-inline" type="date" id="dateOut" name="dateOut">

            <label class="form-inline" for="places"><fmt:message key="main_jsp.form.places"/></label>
            <select class="form-control" id="places" name="places">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
            </select>

            <label class="form-inline" for="type"><fmt:message key="main_jsp.form.room_type"/></label>
            <select class="form-control" id="type" name="roomType">
                <c:forEach var="type" items="${types}">
                    <option value="${type.id}"><c:out value="${type.name}"/></option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary form-inline"><fmt:message key="main_jsp.form.submit_order"/></button>
        </form>
    </c:if>

    <form id="sort" action="controller" method="get">

        <c:if test="${user.roleId == 0}">

            <input type="hidden" name="command" value="showRooms"/>

            <select name="status">
                <option value="all"><fmt:message key="main_jsp.form.filter.all"/></option>
                <option value="VACANT"><fmt:message key="main_jsp.form.filter.vacant"/></option>
                <option value="BOOKED"><fmt:message key="main_jsp.form.filter.booked"/></option>
                <option value="BUSY"><fmt:message key="main_jsp.form.filter.busy"/></option>
                <option value="NOT_AVAILABLE"><fmt:message key="main_jsp.form.filter.not_available"/></option>
            </select>

        </c:if>

        <input type="hidden" name="command" value="showRooms"/>

        <select name="sortBy">
            <option value="type_id"><fmt:message key="main_jsp.form.sort.type"/></option>
            <option value="places"><fmt:message key="main_jsp.form.sort.places"/></option>
            <option value="price"><fmt:message key="main_jsp.form.sort.price"/></option>
        </select>

        <select name="order">
            <option value="ASC"><fmt:message key="main_jsp.form.sort.ascending"/></option>
            <option value="DESC"><fmt:message key="main_jsp.form.sort.descending"/></option>
        </select>

        <input type="hidden" name="pageSize" value=${pageSize}>
        <input type="hidden" name="page" value=${page}>
        <input type="submit" value="<fmt:message key="main_jsp.form.sort.submit_sort"/>">
    </form>

    <table id="rooms-list" class="table">
        <thead class="dark">
        <tr>
            <th scope="col"><fmt:message key="main_jsp.table.type"/></th>
            <th scope="col"><fmt:message key="main_jsp.table.places"/></th>
            <th scope="col"><fmt:message key="main_jsp.table.price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${rooms}">
            <tr>
                <td>${room.type}</td>
                <td>${room.places}</td>
                <td>${room.price}</td>
                <td>
                    <a href="controller?command=roomDescription&roomId=${room.id}&roomStatus=${room.status}"><fmt:message key="main_jsp.table.href"/></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <nav>
        <ul class="pagination">
            <li class="page-item"><util:pagination path="controller?command=showRooms" page="${page}" pageSize="${pageSize}" maxPage="${maxPage}"/></li>
        </ul>
    </nav>
</div>

</body>
</html>
