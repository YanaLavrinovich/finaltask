<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.my.courses" var="myCourses"/>


</head>
<body>
<jsp:include page="/WEB-INF/jsp/component/studentNavBar.jsp">
    <jsp:param name="prev_command" value="SHOW_MY_COURSES_PAGE"/>
</jsp:include>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2><c:out value="${myCourses}"/></h2>
        </div>
        <div class="col-md-12">
            <table class="table table-hover">
                <tbody>
                <jsp:useBean id="trainingList" scope="request" type="java.util.List"/>
                <c:forEach items="${trainingList}" var="training" varStatus="trainingLoopCout">
                    <tr>
                        <th scope="row"><c:out value="${trainingLoopCout.count}"/></th>
                        <td><c:out value="${training.nameCourse}"/></td>
                        <c:if test="${training.courseStatus eq 'in progress'}">
                            <td><span class="badge badge-success"><c:out value="${training.courseStatus}"/></span></td>
                        </c:if>
                        <c:if test="${training.courseStatus eq 'approve'}">
                            <td><span class="badge badge-info"><c:out value="${training.courseStatus}"/></span></td>
                        </c:if>
                        <c:if test="${training.courseStatus eq 'completed'}">
                            <td><span class="badge badge-secondary"><c:out value="${training.courseStatus}"/></span>
                            </td>
                        </c:if>
                        <c:if test="${training.courseStatus eq 'requested'}">
                            <td><span class="badge badge-warning"><c:out value="${training.courseStatus}"/></span></td>
                        </c:if>
                        <c:if test="${training.courseStatus eq 'excluded' or training.courseStatus eq 'leave' or training.courseStatus eq 'reject'}">
                            <td><span class="badge badge-danger"><c:out value="${training.courseStatus}"/></span></td>
                        </c:if>
                        <td><c:out value="${training.dateStart} - ${training.dateFinish}"/></td>
                        <c:if test="${training.mark ne null}">
                            <td><c:out value="${training.mark}"/></td>
                        </c:if>
                        <c:if test="${training.comment ne null}">
                            <td><c:out value="${training.comment}"/></td>
                        </c:if>
                        <c:if test="${training.courseStatus eq 'in progress'}">
                            <td class="text-right">
                                <button class="btn btn-sm btn-danger">Leave</button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>