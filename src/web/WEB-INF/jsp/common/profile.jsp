<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/styles.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="locale.button.remove" var="remove"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.sex" var="sex"/>
    <fmt:message bundle="${loc}" key="locale.message.role" var="role"/>
    <fmt:message bundle="${loc}" key="locale.button.profile" var="profile"/>

</head>
<body>
<jsp:useBean id="user" scope="request" type="by.etc.finaltask.domain.User"/>
<c:if test="${sessionScope.user.role eq 'TEACHER'}">
    <jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>
    <jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
        <jsp:param name="countRequest" value="${countRequest.toString()}"/>
        <jsp:param name="prev_command" value="SHOW_PROFILE,userId=${user.id}"/>
    </jsp:include>
</c:if>
<c:if test="${sessionScope.user.role eq 'STUDENT'}">
    <jsp:include page="/WEB-INF/jsp/component/studentNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_PROFILE,userId=${user.id}"/>
    </jsp:include>
</c:if>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/component/adminNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_PROFILE,userId=${user.id}"/>
    </jsp:include>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1><c:out value="${profile}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${user.firstName} ${user.lastName}"/></h5>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><b><c:out value="${email}"/>: </b><c:out value="${user.email}"/></li>
                    <li class="list-group-item"><b><c:out value="${sex}"/>: </b><c:out value="${user.sex}"/></li>
                    <li class="list-group-item"><b><c:out value="${role}"/>: </b><c:out value="${user.role}"/></li>
                </ul>
                <c:if test="${sessionScope.user.id eq user.id}">
                    <div class="card-body">
                        <a href="controller?command=SHOW_EDIT_PROFILE_PAGE&userId=${user.id}" class="card-link"><c:out
                                value="${edit}"/></a>
                        <c:if test="${sessionScope.user.role ne 'ADMIN'}">
                            <a href="controller?command=REMOVE_USER" class="card-link"><c:out value="${remove}"/></a>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>


<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>

</body>
</html>
