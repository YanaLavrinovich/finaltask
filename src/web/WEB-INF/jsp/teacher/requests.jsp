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
            <h1>Requests</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="accordion" id="accordionExample">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0 justify-content-between">
                            <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                Course 1    <span class="badge badge-info badge-pill">5</span>
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
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
                                <tr>
                                    <th scope="row">3</th>
                                    <td>Larry the Bird</td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn btn-sm btn-info">Accept</button>
                                            <button class="btn btn-sm btn-danger">Reject</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">4</th>
                                    <td>Larry the Bird</td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn btn-sm btn-info">Accept</button>
                                            <button class="btn btn-sm btn-danger">Reject</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">5</th>
                                    <td>Larry the Bird</td>
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
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Course 2    <span class="badge badge-info badge-pill">2</span>
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
                            <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Course 3    <span class="badge badge-info badge-pill">2</span>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
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