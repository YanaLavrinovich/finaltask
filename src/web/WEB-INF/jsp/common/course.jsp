<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.students" var="students"/>
    <fmt:message bundle="${loc}" key="locale.button.set.mark" var="setMark"/>
    <fmt:message bundle="${loc}" key="locale.button.exclude" var="exclude"/>
    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="locale.button.remove" var="remove"/>
    <fmt:message bundle="${loc}" key="locale.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="locale.button.start.training" var="startTraining"/>
    <fmt:message bundle="${loc}" key="locale.button.stop.training" var="stopTraining"/>
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
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/component/adminNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_COURSE,courseId=${course.id}"/>
    </jsp:include>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-md-8 align-left">
            <h1><c:out value="${course.name}"/></h1>
        </div>
        <div class="col-md-4 text-right btns-group">
            <c:if test="${sessionScope.user.role eq 'TEACHER'}">
                <c:if test="${course.progress eq 'planned'}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="START_TRAINING">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button class="btn btn-sm btn-success"><c:out value="${startTraining}"/></button>
                    </form>
                </c:if>
                <c:if test="${course.progress eq 'continues'}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="STOP_TRAINING">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button class="btn btn-sm btn-success"><c:out value="${stopTraining}"/></button>
                    </form>
                </c:if>
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
                <jsp:useBean id="courseStatus" scope="request" class="java.lang.String"/>
                <c:if test="${empty courseStatus}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="SUBMIT_COURSE">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button class="btn btn-sm btn-danger"><c:out value="${submit}"/></button>
                    </form>
                </c:if>
                <c:if test="${courseStatus eq 'requested'}">
                    <p style="color: crimson">You submit on this course!</p>
                </c:if>
                <c:if test="${courseStatus eq 'reject'}">
                    <p style="color: crimson">Sorry! You was be rejected.</p>
                </c:if>
            </c:if>
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
                            <td onclick="location.href='controller?command=SHOW_PROFILE&userId=${student.userId}'">
                                <c:out value="${student.userFirstName} ${student.userLastName}"/></td>
                            <c:if test="${student.courseStatus eq 'in process'}">
                                <td><span class="badge badge-success"><c:out value="${student.courseStatus}"/></span>
                                </td>
                            </c:if>
                            <c:if test="${student.courseStatus eq 'approve'}">
                                <td><span class="badge badge-info"><c:out value="${student.courseStatus}"/></span></td>
                            </c:if>
                            <c:if test="${student.courseStatus eq 'completed'}">
                                <td><span class="badge badge-secondary"><c:out value="${student.courseStatus}"/></span>
                                </td>
                            </c:if>
                            <c:if test="${student.courseStatus eq 'requested'}">
                                <td><span class="badge badge-warning"><c:out value="${student.courseStatus}"/></span>
                                </td>
                            </c:if>
                            <c:if test="${student.courseStatus eq 'excluded' or 'leave'}">
                                <td><span class="badge badge-danger"><c:out value="${student.courseStatus}"/></span>
                                </td>
                            </c:if>
                            <c:if test="${student.mark ne null}">
                                <td><c:out value="${student.mark}"/></td>
                            </c:if>
                            <c:if test="${student.comment ne null}">
                                <td><c:out value="${student.comment}"/></td>
                            </c:if>
                            <td class="text-right btns-group">
                                <c:if test="${course.progress eq 'finished'}">
                                    <form method="post" action="controller">
                                        <input type="hidden" name="command" value="SHOW_MARK_PAGE">
                                        <input type="hidden" name="studentId" value="${student.userId}">
                                        <input type="hidden" name="courseId" value="${course.id}">
                                        <button class="btn btn-sm btn-info"><c:out value="${setMark}"/></button>
                                    </form>
                                </c:if>
                                <c:if test="${course.progress eq 'continues'}">
                                    <form method="post" action="controller">
                                        <input type="hidden" name="command" value="EXCLUDE_STUDENT">
                                        <input type="hidden" name="studentId" value="${student.userId}">
                                        <input type="hidden" name="courseId" value="${course.id}">
                                        <button class="btn btn-sm btn-danger"><c:out value="${exclude}"/></button>
                                    </form>
                                </c:if>
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
