<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap/css/adminpage.css">

    <%--<link rel="script" href="/bootstrap/js/bootstrap.min.js"> --%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">--%>

    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet" href="../../bootstrap/css/adminpage.css">--%>

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <%--<script rel="" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

</head>
<body>
<jsp:useBean id="userBig" class="com.khelenyuk.model.User"></jsp:useBean>
<c:set var="userList" value="${userBig.user}"/>

<%---------------- navbar ----------------------%>
<nav class="navbar fixed-top">
    <div class="container-fluid ">
        <div class="navbar-header ">
            <a class="navbar-brand fitness-buddy navbar-text" href="#">
                <span class="fitness">Fitness</span>Buddy
            </a>
        </div>
        <ul class="nav navbar-nav navbar-right navbar-text">
            <li><a href="#">Home</a></li>
            <li><a href="#">Log out</a></li>
        </ul>
    </div>
</nav>

<%--<div class="jumbotron">--%>
<%--<a class="fitness-buddy" href="#">--%>
<%--<span class="fitness">Fitness</span>Buddy--%>
<%--</a>--%>
<%--</div>--%>

<div class="container">
    <div class="row">

        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h3>Admin page</h3>
                </div>
                <div class="panel-body">

                    <div class="table-responsive">
                        <table id="mytable" class="table table-bordred table-striped">

                            <thead>
                            <th><input type="checkbox" id="checkall"/></th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Login</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th> Action</th>
                            </thead>


                            <tbody>
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td><input type="checkbox" class="checkthis"/></td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.login}</td>
                                    <td>${user.roleId}</td>
                                    <td>${user.email}</td>
                                    <td class="align-right">
                                        <button class=" btn btn-primary btn-block" type="submit"> Block </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td><input type="checkbox" class="checkthis"/></td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.login}</td>
                                <td>${user.roleId}</td>
                                <td>${user.email}</td>
                                <td class="align-right">
                                    <button class="btn btn-danger btn-block" type="submit">Unblock</button>
                                </td>
                            </tr>
                            </tbody>

                        </table>

                        <div class="clearfix"></div>
                        <div class="text-center">
                            <ul class="pagination text-center">
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

</body>
</html>