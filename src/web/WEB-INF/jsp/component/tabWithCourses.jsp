<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/styles.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.courses" var="courses"/>
    <fmt:message bundle="${loc}" key="locale.button.see.course" var="seeCourse"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.start" var="dateStart"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.finish" var="dateFinish"/>
</head>
<body>
<div class="container">
<div class="row">
    <div class="col-md-12 align-left">
        <h1>${courses}</h1>
    </div>
</div>
<div class="row">
    <jsp:useBean id="courseList" scope="request" type="java.util.List"/>
    <c:forEach items="${courseList}" var="course" varStatus="courseLoopCout">
    <div class="col-md-4 col-sm-12">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${course.name}"/></h5>
                <p class="card-text"><c:out value="${dateStart} ${course.dateStart}"/></p>
                <p class="card-text"><c:out value="${dateFinish} ${course.dateFinish}"/></p>
                <form action="controller" method="post">
                    <a href="controller?command=SHOW_COURSE&courseId=${course.id}" class="card-link"><c:out
                            value="${seeCourse}"/></a>
                </form>
            </div>
        </div>
    </div>
    <c:if test="${courseLoopCout.count % 3 == 0}">
</div>
<div class="row">
    </c:if>
    </c:forEach>
</div>
</div>

</body>
</html>
