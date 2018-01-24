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
    <div class="error text-center">${errorBlockUnblockUserMessage}</div>

    <table class="table table-striped table-bordered table-sm">
        <nav class="admin navbar justify-content-center">Admin Page</nav>

        <thead>
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
            <form method="post" action="/controller">
                <input type="hidden" name="userId" value="${user.id}">
                <tr>
                    <th scope="row"><input type="checkbox" class="checkthis"/></th>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.roleId}</td>
                    <td>${user.statusId}</td>
                    <td class="align-right">
                        <c:choose>
                            <c:when test="${user.statusId == 1}">
                                <button class="btn btn-outline-info btn-block"
                                        type="submit"
                                        name="command"
                                        value="block_unblock_user"> Unblocked
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-danger btn-block"
                                        type="submit"
                                        name="command"
                                        value="block_unblock_user"> Blocked
                                </button>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>

    <nav>
        <ul class="pagination pagination-sm justify-content-center">
            <%--<li class="page-item"><a class="page-link" href="#">Previous</a></li>--%>
            <c:forEach begin="1" end="${pages}" step="1" var="page">
                <form action="/controller" method="post">
                    <input type="hidden" name="pageNumber" value="${page}">
                    <li
                            class="page-item
<c:if test="${page == currentPage}">active disabled</c:if>">
                        <button type="submit" class="page-link" name="command" value="TO_N_PAGE">${page}</button>
                    </li>
                </form>
            </c:forEach>
            <%--<li class="page-item"><a class="page-link" href="#">Next</a></li>--%>
        </ul>
    </nav>

</div>

</body>
</html>