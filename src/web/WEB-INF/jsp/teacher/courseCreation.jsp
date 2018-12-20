<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 20.12.2018
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">
</head>
<body>
<div class="navbar navbar-expand navbar-accent text-center">
    <img class="navbar-logo" src="./assets/images/small-logo.png"/>
    <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Courses</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Requests <span class="badge badge-pill badge-light">9</span></a>
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-sm btn-light">+ Create course</button>
            </li>
        </ul>
    </div>
    <div class="navbar-content text-right">
            <span class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="userInfo" data-toggle="dropdown">Yana Laurynovich</a>
                <div class="dropdown-menu" aria-labelledby="userInfo">
                <a class="dropdown-item" href="#">Edit profile</a>
                <a class="dropdown-item" href="#">Logout</a>
                </div>
            </span>
        <span>
                <a class="navbar-lang" href="#">РУ</a>
                <a class="navbar-lang" href="#">EN</a>
            </span>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12 align-left">
            <h1>Create Course</h1>
        </div>
    </div>
    <form>
        <div class="form-group">
            <label for="courseName">Name</label>
            <input type="text" class="form-control" id="courseName" required/>
        </div>
        <div class="form-group">
            <label for="courseDescription">Description</label>
            <textarea class="form-control" id="courseDescription" rows="3" required></textarea>
        </div>
        <div class="row">
            <div class="col-md-6 col-sm-12 form-group">
                <label for="fromDate">Start date</label>
                <input class="form-control" id="fromDate" type="date" required />
            </div>
            <div class="col-md-6 col-sm-12 form-group">
                <label for="toDate">End date</label>
                <input class="form-control" id="toDate" type="date" required />
            </div>
        </div>
        <div class="col-md-12 text-right">
            <button type="submit" class="btn btn-lg btn-info mb-2">Save</button>
        </div>
    </form>
</div>

<script src="./assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
</body>
</html>

