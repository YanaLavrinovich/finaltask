<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/styles.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.message.first.name" var="firstName"/>
    <fmt:message bundle="${loc}" key="locale.button.register" var="register"/>
    <fmt:message bundle="${loc}" key="locale.message.last.name" var="lastName"/>
    <fmt:message bundle="${loc}" key="locale.message.sex" var="sex"/>
    <fmt:message bundle="${loc}" key="locale.message.role" var="role"/>
    <fmt:message bundle="${loc}" key="locale.message.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="locale.error.message.first.name" var="errorFirstName"/>
    <fmt:message bundle="${loc}" key="locale.error.message.last.name" var="errorLastName"/>
    <fmt:message bundle="${loc}" key="locale.error.message.password" var="errorPassword"/>
    <fmt:message bundle="${loc}" key="locale.message.male" var="male"/>
    <fmt:message bundle="${loc}" key="locale.message.female" var="female"/>
    <fmt:message bundle="${loc}" key="locale.message.choose" var="choose"/>
    <fmt:message bundle="${loc}" key="locale.message.teacher" var="teacher"/>
    <fmt:message bundle="${loc}" key="locale.message.student" var="student"/>
    <fmt:message bundle="${loc}" key="locale.error.message.sex" var="errorSex"/>
    <fmt:message bundle="${loc}" key="locale.error.message.role" var="errorRole"/>
    <fmt:message bundle="${loc}" key="locale.error.message.email" var="errorEmail"/>
    <fmt:message bundle="${loc}" key="locale.language.english.short" var="langEn"/>
    <fmt:message bundle="${loc}" key="locale.language.russian.short" var="langRu"/>
    <fmt:message bundle="${loc}" key="locale.error.input" var="errorInput"/>

</head>
<body>
<nav class="navbar navbar-expand navbar-accent">
    <div class="navbar-content text-right">
        <a class="navbar-lang"
           href="controller?command=CHANGE_LANGUAGE&language=ru&page=${pageContext.request.requestURL}">${langRu}</a>
        <a class="navbar-lang"
           href="controller?command=CHANGE_LANGUAGE&language=en&page=${pageContext.request.requestURL}">${langEn}</a>

    </div>
</nav>
<div class="container">
    <form class="form-registration" method="post">
        <div class="row">
            <div class="col-md-12 text-center">
                <img class="mb-4" src="./assets/images/logo.png"/>
                <h1 class="h3 mb-3 font-weight-normal">${registration}</h1>
            </div>
        </div>
        <c:if test="${sessionScope.error==true}">
            <div class="row">
                <div class="col-md-12 text-center">
            <p style="color: crimson"><c:out value="${errorInput}"/></p>
                </div>
            </div>
        </c:if>
        <div>
            <div class="row">
                <div class="form-group mb-3 col-md-6 col-sm-12">
                    <label for="firstName">${firstName}</label>
                    <input type="text" name="firstName" class="form-control" id="firstName"
                           pattern="[a-zA-Z][a-zA-Z '-]*" maxlength="64" placeholder="" value=""
                           required="">
                    <div class="invalid-feedback">
                        ${errorFirstName}
                    </div>
                </div>
                <div class="mb- col-md-6 col-sm-123">
                    <label for="lastName">${lastName}</label>
                    <input type="text" name="lastName" class="form-control" id="lastName"
                           pattern="[a-zA-Z][a-zA-Z '-]*" maxlength="64" placeholder="" value=""
                           required="">
                    <div class="invalid-feedback">
                        ${errorLastName}
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="mb-3 col-md-6 col-sm-12">
                    <label for="email">${email}</label>
                    <input type="email" name="email" maxlength="64" class="form-control" id="email" placeholder="">
                    <div class="invalid-feedback">
                        ${errorEmail}
                    </div>
                </div>
                <div class="mb-3 col-md-6 col-sm-12">
                    <label for="password">${password}</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="">
                    <div class="invalid-feedback">
                        ${errorPassword}
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="mb-3 col-md-6 col-sm-12">
                    <label for="sex">${sex}</label>
                    <select name="sex" class="custom-select d-block w-100" id="sex" required="">
                        <option value="">${choose}</option>
                        <option value="male">${male}</option>
                        <option value="female">${female}</option>
                    </select>
                    <div class="invalid-feedback">
                        ${errorSex}
                    </div>
                </div>
                <div class="mb-3 col-md-6 col-sm-12">
                    <label for="role">${role}</label>
                    <select name="role" class="custom-select d-block w-100" id="role" required="">
                        <option value="">${choose}</option>
                        <option value="teacher">${teacher}</option>
                        <option value="student">${student}</option>
                    </select>
                    <div class="invalid-feedback">
                        ${errorRole}
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 text-right">
                    <input type="hidden" name="command" value="REGISTRATION">
                    <button name="register" formaction="controller" class="btn btn-lg btn-info btn-block"
                            type="submit">${register}
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
