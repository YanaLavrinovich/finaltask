
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.language.english.short" var="langEn"/>
    <fmt:message bundle="${loc}" key="locale.language.russian.short" var="langRu"/>
    <fmt:message bundle="${loc}" key="locale.message.courses" var="courses"/>
    <fmt:message bundle="${loc}" key="locale.message.requests" var="requests"/>
    <fmt:message bundle="${loc}" key="locale.button.create.course.with.plus" var="createCourseWithPlus"/>
    <fmt:message bundle="${loc}" key="locale.button.profile" var="profile"/>
    <fmt:message bundle="${loc}" key="locale.button.log.out" var="logout"/>

</head>
<body>
<form method="post">
    <div class="navbar navbar-expand navbar-accent text-center">
        <img class="navbar-logo" src="./assets/images/small-logo.png"/>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">${courses}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">${requests}<span class="badge badge-pill badge-light">9</span></a>
                </li>
                <li class="nav-item">
                    <button type="button" value="go" onclick="location.href='creationCourse'" class="btn btn-sm btn-light">${createCourseWithPlus}</button>
                </li>
            </ul>
        </div>
        <div class="navbar-content text-right">
            <span class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="userInfo"
                   data-toggle="dropdown">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                <div class="dropdown-menu" aria-labelledby="userInfo">
                <a class="dropdown-item" href="#">${profile}</a>
                <a class="dropdown-item" href="controller?command=LOGOUT">${logout}</a>
                </div>
            </span>
            <span>
            <a class="navbar-lang"
               href="controller?command=CHANGE_LANGUAGE&language=ru&prev_command=SHOW_HOME_PAGE">${langRu}</a>
            <a class="navbar-lang"
               href="controller?command=CHANGE_LANGUAGE&language=en&prev_command=SHOW_HOME_PAGE">${langEn}</a>
   </span>
        </div>
    </div>
</form>

<c:if test="${sessionScope.user.role eq 'TEACHER'}">
    <jsp:include page="/WEB-INF/jsp/teacher/courses.jsp"/>
</c:if>

</body>
</html>