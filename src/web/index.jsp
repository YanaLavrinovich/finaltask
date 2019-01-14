<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.button.sign.in" var="signIn"/>
    <fmt:message bundle="${loc}" key="locale.button.register" var="register"/>
    <fmt:message bundle="${loc}" key="locale.message.register" var="messageRegister"/>
    <fmt:message bundle="${loc}" key="locale.message.sign.in" var="messageSignIn"/>
    <fmt:message bundle="${loc}" key="locale.language.english.short" var="langEn"/>
    <fmt:message bundle="${loc}" key="locale.language.russian.short" var="langRu"/>

</head>
<body class="text-center">
<nav class="navbar navbar-expand navbar-accent">
    <div class="navbar-content text-right">
        <a class="navbar-lang"
           href="controller?command=CHANGE_LANGUAGE&language=ru&page=${pageContext.request.requestURL}">${langRu}</a>
        <a class="navbar-lang"
           href="controller?command=CHANGE_LANGUAGE&language=en&page=${pageContext.request.requestURL}">${langEn}</a>
    </div>
</nav>
<div class="container text-center">
    <form class="form-signin" method="post">
        <img class="mb-4" src="./assets/images/logo.png"/>
        <h1 class="h3 mb-3 font-weight-normal">${messageSignIn}</h1>
        <c:if test="${sessionScope.error==true}">
            <p style="color: crimson">Wrong email or password!</p>
        </c:if>
        <label for="inputLogin" class="sr-only">${email}</label>
        <input name="email" type="email" id="inputLogin" class="form-control" placeholder=${email}>
        <label for="inputPassword" class="sr-only">${password}</label>
        <input name="password" type="password" id="inputPassword" class="form-control" value="" placeholder=${password}>
        <div class="mb-3">
            <input type="hidden" name="command" value="AUTHORIZATION">
            <button name="signin" formaction="controller" class="btn btn-lg btn-info btn-block"
                    type="submit">${signIn}</button>
        </div>
        <div class="mb-3">
            <p>${messageRegister} <a href="registration">${register}</a></p>
        </div>
    </form>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>

