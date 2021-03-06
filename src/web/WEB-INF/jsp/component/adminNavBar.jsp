<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/styles.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.language.english.short" var="langEn"/>
    <fmt:message bundle="${loc}" key="locale.language.russian.short" var="langRu"/>
    <fmt:message bundle="${loc}" key="locale.message.courses" var="courses"/>
    <fmt:message bundle="${loc}" key="locale.message.users" var="users"/>
    <fmt:message bundle="${loc}" key="locale.button.profile" var="profile"/>
    <fmt:message bundle="${loc}" key="locale.button.log.out" var="logout"/>

</head>
<body>
<div class="navbar navbar-expand navbar-accent text-center">
    <img class="navbar-logo" src="./assets/images/small-logo.png"/>
    <div class="collapse navbar-collapse" id="navbar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link"
                   href="controller?command=SHOW_HOME_PAGE">${courses}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=SHOW_ALL_USERS">${users}</a>
            </li>
        </ul>
    </div>
    <div class="navbar-content text-right">
            <span class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="userInfo"
                   data-toggle="dropdown">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                <div class="dropdown-menu" aria-labelledby="userInfo">
                <a class="dropdown-item"
                   href="controller?command=SHOW_PROFILE&userId=${sessionScope.user.id}">${profile}</a>
                <a class="dropdown-item" href="controller?command=LOGOUT">${logout}</a>
                </div>
        </span>
        <span>
            <a class="navbar-lang"
               href="controller?command=CHANGE_LANGUAGE&language=ru&prev_command=${param.prev_command}">${langRu}</a>
            <a class="navbar-lang"
               href="controller?command=CHANGE_LANGUAGE&language=en&prev_command=${param.prev_command}">${langEn}</a>
        </span>
    </div>
</div>
</body>

</html>
