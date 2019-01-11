<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
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
    <fmt:message bundle="${loc}" key="locale.button.accept" var="accept"/>
    <fmt:message bundle="${loc}" key="locale.button.reject" var="reject"/>

</head>
<body>
<jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>
<jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
    <jsp:param name="countRequest" value="${countRequest.toString()}"/>
    <jsp:param name="prev_command" value="SHOW_REQUEST"/>
</jsp:include>


<div class="container">
    <div class="row">
        <div class="col-md-12 align-left">
            <h1><c:out value="${requests}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <jsp:useBean id="requestMap" scope="request" class="java.util.HashMap"/>
            <c:forEach items="${requestMap}" var="requestEntry">
                <div class="accordion" id="${requestEntry.key.id}">
                    <div class="card">
                        <div class="card-header" id="heading${requestEntry.key.id}">
                            <h5 class="mb-0 justify-content-between">
                                <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                        data-target="#collapse${requestEntry.key.id}"
                                        aria-expanded="true" aria-controls="collapse${requestEntry.key.id}">
                                    <c:out value="${requestEntry.key.name} "/>
                                    <c:forEach items="${requestEntry.value}" var="requestEntity"
                                               varStatus="requestLoop">
                                    <span class="badge badge-info badge-pill"><c:out value="${requestLoop.count}"/>
                                    </span>
                                    </c:forEach>
                                </button>
                            </h5>
                        </div>
                        <div id="collapse${requestEntry.key.id}" class="collapse"
                             area-labelledby="heading${requestEntry.key.id}">
                            <div class="card-body">
                                <table class="table table-hover">
                                    <tbody>
                                    <c:forEach items="${requestEntry.value}" var="requestEntity"
                                               varStatus="requestLoop">
                                        <tr>
                                            <th scope="row"><c:out value="${requestLoop.count}"/></th>
                                            <td><c:out
                                                    value="${requestEntity.firstName} ${requestEntity.lastName}"/></td>
                                            <td class="text-right">
                                                <div class="btn-group">
                                                    <form action="controller" method="post">
                                                        <input type="hidden" name="command" value="ACCEPT_SUBSCRIBER">
                                                        <input type="hidden" name="studentId"
                                                               value="${requestEntity.id}">
                                                        <input type="hidden" name="courseId"
                                                               value="${requestEntry.key.id}">
                                                        <button class="btn btn-sm btn-info"><c:out
                                                                value="${accept}"/></button>
                                                    </form>
                                                    <form action="controller" method="post">
                                                        <input type="hidden" name="command" value="REJECT_SUBSCRIBER">
                                                        <input type="hidden" name="studentId"
                                                               value="${requestEntity.id}">
                                                        <input type="hidden" name="courseId"
                                                               value="${requestEntry.key.id}">
                                                        <button class="btn btn-sm btn-danger">
                                                            <c:out value="${reject}"/></button>
                                                    </form>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>