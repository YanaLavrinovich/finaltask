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
    <fmt:message bundle="${loc}" key="locale.message.edit.course" var="editCourse"/>
    <fmt:message bundle="${loc}" key="locale.message.course.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.message.course.description" var="description"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.start" var="dateStart"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.finish" var="dateFinish"/>
    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>

</head>
<body>

<jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="course" scope="request" type="by.etc.finaltask.bean.Course"/>

<jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
    <jsp:param name="countRequest" value="${countRequest.toString()}"/>
    <jsp:param name="prev_command" value="SHOW_EDIT_COURSE_PAGE,courseId=${course.id}"/>
</jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-12 align-left">
            <h1><c:out value="${editCourse}"/></h1>
        </div>
    </div>
    <form method="post">
        <div class="form-group">
            <label for="courseName"><c:out value="${name}"/></label>
            <input type="text" name="name" class="form-control" id="courseName" value="${course.name}" required/>
        </div>
        <div class="form-group">
            <label for="courseDescription"><c:out value="${description}"/></label>
            <textarea class="form-control" name="description" id="courseDescription" rows="3" required><c:out
                    value="${course.description}"/></textarea>
        </div>
        <div class="row">
            <div class="col-md-6 col-sm-12 form-group">
                <label for="fromDate"><c:out value="${dateStart}"/></label>
                <input class="form-control" name="dateStart" id="fromDate" type="date" value="${course.dateStart}"
                       required/>
            </div>
            <div class="col-md-6 col-sm-12 form-group">
                <label for="toDate"><c:out value="${dateFinish}"/></label>
                <input class="form-control" name="dateFinish" id="toDate" type="date" value="${course.dateFinish}"
                       required/>
            </div>
        </div>
        <div class="col-md-12 text-right">
            <button type="submit" formaction="controller" class="btn btn-lg btn-info mb-2"><c:out
                    value="${edit}"/></button>
            <input type="hidden" name="command" value="EDIT_COURSE">
            <input type="hidden" name="courseId" value="${course.id}">
        </div>
    </form>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>
