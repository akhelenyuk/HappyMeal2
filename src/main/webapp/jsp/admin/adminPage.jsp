<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap/css/adminpage.css">
    <link rel="stylesheet" href="/bootstrap/css/header.css">

</head>
<body>

<jsp:include page="../headerNavbar.jsp"/>
<br/>
<div class="container">

    <table class="table table-striped table-bordered table-sm">
        <nav class="admin navbar justify-content-center">Admin Page</nav>

        <thead >
        <th scope="col"><input type="checkbox" id="checkall"/></th>
        <th scope="col">First name</th>
        <th scope="col">Last name</th>
        <th scope="col">Login</th>
        <th scope="col">Role</th>
        <th scope="col">Status</th>
        <th scope="col" class="text-center"> Action</th>
        </thead>

        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <th scope="row"><input type="checkbox" class="checkthis"/></th>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.login}</td>
                <td>${user.roleId}</td>
                <td>${user.email}</td>
                <td class="align-right">
                    <button class="btn btn-outline-info btn-block" type="submit" name="command" value="block_unblock_user"> Unblocked</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <th scope="row"><input type="checkbox" class="checkthis"/></th>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.login}</td>
            <td>${user.roleId}</td>
            <td>${user.email}</td>
            <td class="align-right">
                <button class="btn btn-danger btn-block" type="submit">Blocked</button>
            </td>
        </tr>
        </tbody>
    </table>

    <nav>
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </nav>

</div>
</body>
</html>