<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 03.12.2018
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link href="./assets/css/registration.css" rel="stylesheet">
</head>
<body>
<form class="container" method="post">
    <div class="col-md-8 order-md-1">
        <h4 class="h3 mb-3 font-weight-normal">Personal data</h4>
        <div class="mb-3">
            <label for="firstName">First name</label>
            <input type="text" class="form-control" id="firstName" placeholder="" value="" required="">
            <div class="invalid-feedback">
                Valid first name is required.
            </div>
        </div>
        <div class="mb-3">
            <label for="lastName">Last name</label>
            <input type="text" class="form-control" id="lastName" placeholder="" value="" required="">
            <div class="invalid-feedback">
                Valid last name is required.
            </div>
        </div>
        <div class="mb-3">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" placeholder="">
            <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
            </div>
        </div>
        <div class="mb-3">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="">
            <div class="invalid-feedback">
                Please enter a valid password.
            </div>
        </div>
        <div class="mb-3">
            <label for="sex">Sex</label>
            <select class="custom-select d-block w-100" id="sex" required="">
                <option value="">Choose...</option>
                <option>Man</option>
                <option>Woman</option>
            </select>
            <div class="invalid-feedback">
                Please select a valid sex.
            </div>
        </div>
        <div class="mb-3">
            <label for="role">Role</label>
            <select class="custom-select d-block w-100" id="role" required="">
                <option value="">Choose...</option>
                <option>Teacher</option>
                <option>Student</option>
            </select>
            <div class="invalid-feedback">
                Please select a valid role.
            </div>
        </div>
        <button name="register" class="btn btn-lg btn-info btn-block" type="submit">Register!</button>
    </div>
</form>
</body>
</html>
