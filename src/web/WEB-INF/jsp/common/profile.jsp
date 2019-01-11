<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="../../../assets/css/bootstrap.min.css">
    <link href="../../../assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="locale.button.remove" var="remove"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.sex" var="sex"/>
    <fmt:message bundle="${loc}" key="locale.message.role" var="role"/>

</head>
<body>
<c:if test="${sessionScope.user.role eq 'TEACHER'}">
    <jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>
    <jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
        <jsp:param name="countRequest" value="${countRequest.toString()}"/>
        <jsp:param name="prev_command" value="SHOW_PROFILE"/>
    </jsp:include>
</c:if>
<jsp:useBean id="user" scope="request" type="by.etc.finaltask.bean.User"/>
<div class="container">
    <div class="row">
        <div class="col-md-10 align-left">
            <h1><c:out value="${user.firstName} ${user.lastName}"/></h1>
        </div>
        <c:if test="${sessionScope.user.id eq user.id}">
            <div class="col-md-2 text-right">
                <div class="btn-group">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="EDIT_PROFILE">
                        <input type="hidden" name="courseId" value="${user.id}">
                        <button class="btn btn-sm btn-info"><c:out value="${edit}"/></button>
                    </form>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="REMOVE_PROFILE">
                        <input type="hidden" name="courseId" value="${user.id}">
                        <button class="btn btn-sm btn-danger"><c:out value="${remove}"/></button>
                    </form>
                </div>
            </div>
        </c:if>
    </div>

    <div class="row">
        <div class="col-md-12 align-left">
            <h5><c:out value="${email} = ${user.email}"/></h5>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 align-left">
            <h5><c:out value="${sex} = ${user.sex}"/></h5>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 align-left">
            <h5><c:out value="${role} = ${user.role}"/></h5>
        </div>
    </div>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>

</body>
</html>
