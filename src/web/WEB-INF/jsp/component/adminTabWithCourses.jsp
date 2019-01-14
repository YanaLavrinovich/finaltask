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
    <fmt:message bundle="${loc}" key="locale.button.see.course" var="seeCourse"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.start" var="dateStart"/>
    <fmt:message bundle="${loc}" key="locale.message.course.date.finish" var="dateFinish"/>
    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="locale.button.remove" var="remove"/>
    <fmt:message bundle="${loc}" key="locale.button.restore" var="restore"/>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2><c:out value="${courses}"/></h2>
        </div>
        <div class="col-md-12">
            <table class="table table-hover">
                <tbody>
                <jsp:useBean id="courseList" scope="request" type="java.util.List"/>
                <c:forEach items="${courseList}" var="course" varStatus="courseLoopCout">
                    <tr>
                        <th scope="row"><c:out value="${courseLoopCout.count}"/></th>
                        <td onclick="location.href='controller?command=SHOW_COURSE&courseId=${course.id}'">
                            <c:out value="${course.name}"/></td>
                        <td class="text-right btns-group">
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="SHOW_EDIT_COURSE_PAGE">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button class="btn btn-sm btn-info"><c:out value="${edit}"/></button>
                            </form>
                            <c:if test="${not course.isDeleted}">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="REMOVE_COURSE">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <button class="btn btn-sm btn-danger"><c:out value="${remove}"/></button>
                                </form>
                            </c:if>
                            <c:if test="${course.isDeleted}">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="RESTORE_COURSE">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <button class="btn btn-sm btn-danger"><c:out value="${restore}"/></button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
