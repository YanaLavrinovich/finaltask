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
<div class="navbar navbar-expand navbar-accent text-center">
    <img class="navbar-logo" src="./assets/images/small-logo.png"/>
    <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#"><c:out value="${courses}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><c:out value="${requests}"/></a>
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-sm btn-light"><c:out value="${createCourseWithPlus}"/></button>
            </li>
        </ul>
    </div>
    <div class="navbar-content text-right">
            <span class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="userInfo"
                   data-toggle="dropdown">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                <div class="dropdown-menu" aria-labelledby="userInfo">
                <a class="dropdown-item" href="#"><c:out value="${profile}"/></a>
                <a class="dropdown-item" href="#"><c:out value="${logout}"/></a>
                </div>
            </span>
        <span>
             <a class="navbar-lang"
                href="controller?command=CHANGE_LANGUAGE&language=ru&page=${pageContext.request.requestURL}"><c:out
                     value="${langRu}"/></a>
             <a class="navbar-lang"
                href="controller?command=CHANGE_LANGUAGE&language=en&page=${pageContext.request.requestURL}"><c:out
                     value="${langEn}"/></a>
             </span>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12 align-left">
            <h1><c:out value="${requests}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="accordion" id="accordionExample">
                <jsp:useBean id="test" scope="request" class="java.util.HashMap"/>
                <c:forEach items="${test}" var="requestEntry">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0 justify-content-between">
                                <button class="btn btn-link" type="button" data-toggle="collapse"
                                        data-target="#collapseOne"
                                        aria-expanded="true" aria-controls="collapseOne">
                                    <c:out value="${requestEntry.key}"/><span
                                        class="badge badge-info badge-pill">5</span>
                                </button>
                            </h5>
                        </div>
                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne"
                             data-parent="#accordionExample">
                            <div class="card-body">
                                <table class="table table-hover">
                                    <tbody>
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>Mark</td>
                                        <td class="text-right">
                                            <div class="btn-group">
                                                <button class="btn btn-sm btn-info"><c:out value="${accept}"/></button>
                                                <button class="btn btn-sm btn-danger"><c:out
                                                        value="${reject}"/></button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>Jacob</td>
                                        <td class="text-right">
                                            <div class="btn-group">
                                                <button class="btn btn-sm btn-info">Accept</button>
                                                <button class="btn btn-sm btn-danger">Reject</button>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                    data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Course 2 <span class="badge badge-info badge-pill">2</span>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                        <div class="card-body">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Mark</td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn btn-sm btn-info">Accept</button>
                                            <button class="btn btn-sm btn-danger">Reject</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>Jacob</td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn btn-sm btn-info">Accept</button>
                                            <button class="btn btn-sm btn-danger">Reject</button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingThree">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                    data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Course 3 <span class="badge badge-info badge-pill">2</span>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree"
                         data-parent="#accordionExample">
                        <div class="card-body">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Mark</td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn btn-sm btn-info">Accept</button>
                                            <button class="btn btn-sm btn-danger">Reject</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>Jacob</td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn btn-sm btn-info">Accept</button>
                                            <button class="btn btn-sm btn-danger">Reject</button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>