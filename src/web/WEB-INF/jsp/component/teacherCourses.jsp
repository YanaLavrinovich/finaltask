<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.courses" var="courses"/>

</head>
<body>
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
                <h6 class="card-subtitle mb-2 text-muted"><c:out value="${courseLoopCout.count}"/></h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
                <a href="#" class="card-link">Card link</a>
            </div>
        </div>
    </div>
    <c:if test="${courseLoopCout.count % 3 == 0}">
    </div>
    <div class="row">
        </c:if>
        </c:forEach>
</body>
</html>
