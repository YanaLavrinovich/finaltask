<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

</head>
<body>

<c:if test="${sessionScope.user.role eq 'TEACHER'}">
    <jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>
    <jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
        <jsp:param name="countRequest" value="${countRequest.toString()}"/>
        <jsp:param name="prev_command" value="SHOW_HOME_PAGE"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/component/tabWithCourses.jsp"/>
</c:if>

<c:if test="${sessionScope.user.role eq 'STUDENT'}">
    <jsp:include page="/WEB-INF/jsp/component/studentNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_HOME_PAGE"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/component/tabWithCourses.jsp"/>
</c:if>

<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/component/adminNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_HOME_PAGE"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/component/adminTabWithCourses.jsp"/>
</c:if>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>