<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
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
    <fmt:message bundle="${loc}" key="locale.message.create.course" var="createCourse"/>
    <fmt:message bundle="${loc}" key="locale.message.course.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.message.course.description" var="description"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.start" var="dateStart"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.end" var="dateEnd"/>
    <fmt:message bundle="${loc}" key="locale.button.course.add" var="add"/>

</head>
<body>
<div class="navbar navbar-expand navbar-accent text-center">
    <img class="navbar-logo" src="./assets/images/small-logo.png"/>
    <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#"><c:out value="${courses}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><c:out value="${requests}"/><span
                        class="badge badge-pill badge-light">9</span></a>
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-sm btn-light"><c:out value="${createCourseWithPlus}"/></button>
            </li>
        </ul>
    </div>
    <div class="navbar-content text-right">
            <span class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="userInfo"
                   data-toggle="dropdown">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                <div class="dropdown-menu" aria-labelledby="userInfo">
                <a class="dropdown-item" href="#"><c:out value="${profile}"/></a>
                <a class="dropdown-item" href="#"><c:out value="${logout}"/></a>
                </div>
            </span>
        <span>
                <a class="navbar-lang"
                   href="controller?command=CHANGE_LANGUAGE&language=ru&page=${pageContext.request.requestURL}"><c:out
                        value="${langRu}"/></a>
                <a class="navbar-lang"
                   href="controller?command=CHANGE_LANGUAGE&language=en&page=${pageContext.request.requestURL}"><c:out
                        value="${langEn}"/></a>
            </span>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12 align-left">
            <h1><c:out value="${createCourse}"/></h1>
        </div>
    </div>
    <form method="post">
        <div class="form-group">
            <label for="courseName"><c:out value="${name}"/></label>
            <input type="text" name="name" class="form-control" id="courseName" required/>
        </div>
        <div class="form-group">
            <label for="courseDescription"><c:out value="${description}"/></label>
            <textarea class="form-control" name="description" id="courseDescription" rows="3" required></textarea>
        </div>
        <div class="row">
            <div class="col-md-6 col-sm-12 form-group">
                <label for="fromDate"><c:out value="${dateStart}"/></label>
                <input class="form-control" name="dateStart" id="fromDate" type="date" required/>
            </div>
            <div class="col-md-6 col-sm-12 form-group">
                <label for="toDate"><c:out value="${dateEnd}"/></label>
                <input class="form-control" name="dateFinish" id="toDate" type="date" required/>
            </div>
        </div>
        <div class="col-md-12 text-right">
            <button type="submit" formaction="controller" class="btn btn-lg btn-info mb-2"><c:out
                    value="${add}"/></button>
            <input type="hidden" name="command" value="CREATE_COURSE">
            <input type="hidden" name="userId" value="${sessionScope.user.id}">
        </div>
    </form>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>

