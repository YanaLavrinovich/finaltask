<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="../../../assets/css/bootstrap.min.css">
    <link href="../../../assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.students" var="students"/>
    <fmt:message bundle="${loc}" key="locale.button.set.mark" var="setMark"/>
    <fmt:message bundle="${loc}" key="locale.button.exclude" var="exclude"/>
    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="locale.button.remove" var="remove"/>
    <fmt:message bundle="${loc}" key="locale.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.start" var="dateStart"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.finish" var="dateFinish"/>

</head>
<body>
<jsp:useBean id="course" scope="request" class="by.etc.finaltask.bean.Course"/>

<c:if test="${sessionScope.user.role eq 'TEACHER'}">
    <jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>
    <jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
        <jsp:param name="countRequest" value="${countRequest.toString()}"/>
        <jsp:param name="prev_command" value="SHOW_COURSE,courseId=${course.id}"/>
    </jsp:include>
</c:if>
<c:if test="${sessionScope.user.role eq 'STUDENT'}">
    <jsp:include page="/WEB-INF/jsp/component/studentNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_COURSE,courseId=${course.id}"/>
    </jsp:include>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-10 align-left">
            <h1><c:out value="${course.name}"/></h1>
        </div>
        <div class="col-md-2 text-right">
            <div class="btn-group">
                <c:if test="${sessionScope.user.role eq 'TEACHER'}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="SHOW_EDIT_COURSE_PAGE">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button class="btn btn-sm btn-info"><c:out value="${edit}"/></button>
                    </form>

                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="REMOVE_COURSE">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button class="btn btn-sm btn-danger"><c:out value="${remove}"/></button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'STUDENT'}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="SUBMIT_COURSE">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button class="btn btn-sm btn-danger"><c:out value="${submit}"/></button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 align-left">
            <b><c:out value="${dateStart}: "/></b>${course.dateStart}
            <b><c:out value="${dateFinish}: "/></b>${course.dateFinish}
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 align-left">
            <p><c:out value="${course.description}"/></p>
        </div>
    </div>
    <c:if test="${sessionScope.user.role eq 'TEACHER'}">
        <div class="row">
            <div class="col-md-12">
                <h2><c:out value="${students}"/></h2>
            </div>
            <div class="col-md-12">
                <table class="table table-hover">
                    <tbody>
                    <jsp:useBean id="studentList" scope="request" type="java.util.List"/>
                    <c:forEach items="${studentList}" var="student" varStatus="studentLoopCout">
                        <tr>
                            <th scope="row"><c:out value="${studentLoopCout.count}"/></th>
                            <td onclick="location.href='controller?command=SHOW_PROFILE&userId=${student.id}'">
                                <c:out value="${student.firstName} ${student.lastName}"/></td>
                            <td class="text-right">
                                <div class="btn-group">
                                    <form method="post" action="controller">
                                        <input type="hidden" name="command" value="SHOW_MARK_PAGE">
                                        <input type="hidden" name="studentId" value="${student.id}">
                                        <input type="hidden" name="courseId" value="${course.id}">
                                        <button class="btn btn-sm btn-info"><c:out value="${setMark}"/></button>
                                    </form>
                                    <form method="post" action="controller">
                                        <input type="hidden" name="command" value="EXCLUDE_STUDENT">
                                        <input type="hidden" name="studentId" value="${student.id}">
                                        <input type="hidden" name="courseId" value="${course.id}">
                                        <button class="btn btn-sm btn-danger"><c:out value="${exclude}"/></button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>
