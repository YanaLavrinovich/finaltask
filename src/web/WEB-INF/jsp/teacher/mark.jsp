<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/styles.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.mark" var="mark"/>
    <fmt:message bundle="${loc}" key="locale.message.comment" var="comment"/>
    <fmt:message bundle="${loc}" key="locale.button.save" var="save"/>
    <fmt:message bundle="${loc}" key="locale.error.input" var="errorInput"/>

</head>
<body>
<jsp:useBean id="course" scope="request" type="by.etc.finaltask.domain.Course"/>
<jsp:useBean id="student" scope="request" type="by.etc.finaltask.domain.User"/>
<jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>

<jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
    <jsp:param name="countRequest" value="${countRequest.toString()}"/>
    <jsp:param name="prev_command" value="SHOW_MARK_PAGE,studentId=${student.id},courseId=${course.id}"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12 align-left">
            <h1><c:out value="${student.firstName} ${student.lastName}"/></h1>
            <h3><c:out value="${course.name}"/></h3>
        </div>
    </div>
    <div class="row">
        <c:if test="${sessionScope.error==true}">
            <p style="color: crimson"><c:out value="${errorInput}"/></p>
        </c:if>
    </div>
    <form method="post" action="controller">
        <div class="form-group">
            <label for="mark"><c:out value="${mark}"/></label>
            <input type="number" name="mark" class="form-control" id="mark" required/>
        </div>
        <div class="form-group">
            <label for="comment"><c:out value="${comment}"/></label>
            <textarea class="form-control" name="comment" id="comment" rows="10"></textarea>
        </div>
        <input type="hidden" name="command" value="SET_MARK">
        <input type="hidden" name="courseId" value="${course.id}">
        <input type="hidden" name="studentId" value="${student.id}">
        <div class="col-md-12 text-right">
            <button type="submit" class="btn btn-lg btn-info mb-2"><c:out value="${save}"/></button>
        </div>
    </form>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>
