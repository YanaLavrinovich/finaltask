<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.first.name" var="firstName"/>
    <fmt:message bundle="${loc}" key="locale.message.last.name" var="lastName"/>
    <fmt:message bundle="${loc}" key="locale.message.sex" var="sex"/>
    <fmt:message bundle="${loc}" key="locale.error.message.first.name" var="errorFirstName"/>
    <fmt:message bundle="${loc}" key="locale.error.message.last.name" var="errorLastName"/>
    <fmt:message bundle="${loc}" key="locale.message.male" var="male"/>
    <fmt:message bundle="${loc}" key="locale.message.female" var="female"/>
    <fmt:message bundle="${loc}" key="locale.message.choose" var="choose"/>
    <fmt:message bundle="${loc}" key="locale.message.teacher" var="teacher"/>
    <fmt:message bundle="${loc}" key="locale.message.student" var="student"/>
    <fmt:message bundle="${loc}" key="locale.error.message.sex" var="errorSex"/>
    <fmt:message bundle="${loc}" key="locale.error.message.email" var="errorEmail"/>
    <fmt:message bundle="${loc}" key="locale.message.edit.profile" var="editProfile"/>
    <fmt:message bundle="${loc}" key="locale.button.edit" var="edit"/>

</head>
<body>
<jsp:useBean id="user" scope="request" type="by.etc.finaltask.bean.User"/>
<c:if test="${sessionScope.user.role eq 'TEACHER'}">
    <jsp:useBean id="countRequest" scope="request" type="java.lang.Integer"/>
    <jsp:include page="/WEB-INF/jsp/component/teacherNavBar.jsp">
        <jsp:param name="countRequest" value="${countRequest.toString()}"/>
        <jsp:param name="prev_command" value="SHOW_EDIT_PROFILE_PAGE,userId=${user.id}"/>
    </jsp:include>
</c:if>
<c:if test="${sessionScope.user.role eq 'STUDENT'}">
    <jsp:include page="/WEB-INF/jsp/component/studentNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_EDIT_PROFILE_PAGE,userId=${user.id}"/>
    </jsp:include>
</c:if>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <jsp:include page="/WEB-INF/jsp/component/adminNavBar.jsp">
        <jsp:param name="prev_command" value="SHOW_EDIT_PROFILE_PAGE,userId=${user.id}"/>
    </jsp:include>
</c:if>

<div class="container">
    <form class="form-edit" method="post" action="controller">
        <div class="row">
            <div class="col-md-12 text-center">
                <img class="mb-4" src="./assets/images/logo.png"/>
                <h1 class="h3 mb-3 font-weight-normal"><c:out value="${editProfile}"/></h1>
            </div>
        </div>
        <div>
            <div class="row">
                <div class="form-group mb-3 col-md-6 col-sm-12">
                    <label for="firstName"><c:out value="${firstName}"/></label>
                    <input type="text" name="firstName" class="form-control" id="firstName" placeholder=""
                           value="${user.firstName}"
                           required="">
                    <div class="invalid-feedback">
                        ${errorFirstName}
                    </div>
                </div>
                <div class="mb- col-md-6 col-sm-123">
                    <label for="lastName"><c:out value="${lastName}"/></label>
                    <input type="text" name="lastName" class="form-control" id="lastName" placeholder=""
                           value="${user.lastName}"
                           required="">
                    <div class="invalid-feedback">
                        ${errorLastName}
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="mb-3 col-md-6 col-sm-12">
                    <label for="email"><c:out value="${email}"/></label>
                    <input type="text" name="email" class="form-control" id="email" placeholder="" value="${user.email}">
                    <div class="invalid-feedback">
                        ${errorEmail}
                    </div>
                </div>
                <div class="mb-3 col-md-6 col-sm-12">
                    <label for="sex">${sex}</label>
                    <select name="sex" class="custom-select d-block w-100" id="sex" required="">
                        <option value="">${choose}</option>
                        <option>${female}</option>
                        <option>${male}</option>
                    </select>
                    <div class="invalid-feedback">
                        ${errorSex}
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 text-right">
                    <input type="hidden" name="command" value="EDIT_PROFILE">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button class="btn btn-lg btn-info btn-block"
                            type="submit"><c:out value="${edit}!"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>
