<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/styles.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.language.english.short" var="langEn"/>
    <fmt:message bundle="${loc}" key="locale.language.russian.short" var="langRu"/>
    <fmt:message bundle="${loc}" key="locale.error.message.no.access" var="noAccess"/>

</head>
<body class="text-center">
<nav class="navbar navbar-expand navbar-accent">
</nav>
<div class="col-sm-8 mx-auto">
    <h1>${noAccess}</h1>
</div>

<script src="/assets/js/bootstrap.min.js"/>
</body>
</html>