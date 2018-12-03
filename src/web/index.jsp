<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 29.11.2018
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" method="post" action="Authorization">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputLogin" class="sr-only">Login</label>
    <input name="login" type="text" id="inputLogin" class="form-control" placeholder="Login" required="" autofocus="">
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
    <div class="mb-3">
        <input type="hidden" name="command" value="AUTHORIZATION">
        <button name="signin" formaction="Authorization" class="btn btn-lg btn-info btn-block" type="submit">Sign in</button>
    </div>
    <div class="mb-3">

        <button name="register" formaction="Registration" class="btn btn-lg btn-info btn-block" type="submit">Register!</button>

    </div>
</form>
<script src="./assets/js/bootstrap.min.js"/>
</body>
</html>
