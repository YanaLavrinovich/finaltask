<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.users" var="users"/>
    <fmt:message bundle="${loc}" key="locale.button.see.course" var="seeCourse"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.start" var="dateStart"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.finish" var="dateFinish"/>
    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="locale.button.remove" var="remove"/>
    <fmt:message bundle="${loc}" key="locale.button.restore" var="restore"/>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/component/adminNavBar.jsp">
    <jsp:param name="prev_command" value="SHOW_ALL_USERS"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2><c:out value="${users}"/></h2>
        </div>
        <div class="col-md-12">
            <table class="table table-hover">
                <tbody>
                <jsp:useBean id="userList" scope="request" type="java.util.List"/>
                <c:forEach items="${userList}" var="user" varStatus="userLoopCout">
                    <tr>
                        <th scope="row"><c:out value="${userLoopCout.count}"/></th>
                        <td onclick="location.href='controller?command=SHOW_PROFILE&userId=${user.id}'">
                            <c:out value="${user.firstName} ${user.lastName}"/></td>
                        <td class="text-right btns-group">
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="SHOW_EDIT_PROFILE_PAGE">
                                <input type="hidden" name="userId" value="${user.id}">
                                <button class="btn btn-sm btn-info"><c:out value="${edit}"/></button>
                            </form>
                            <c:if test="${user.isDeleted eq false}">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="REMOVE_USER">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="userRole" value="${user.role}">
                                    <button class="btn btn-sm btn-danger"><c:out value="${remove}"/></button>
                                </form>
                            </c:if>
                            <c:if test="${user.isDeleted eq true}">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="RESTORE_USER">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <button class="btn btn-sm btn-danger"><c:out value="${restore}"/></button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>
