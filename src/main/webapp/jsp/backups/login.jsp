<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="loginpagecontent"/>

<html>
<head>
    <title><fmt:message key="login.title"/></title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/jsp/backups/login.css">
</head>
<body>
<%--<div id="fullscreen_bg" class="fullscreen_bg"/>--%>
<div class="row">
    <nav class="navbar container-fluid navbar-header">
        <div class="navbar-brand fitness-buddy"><span class="fitness">Fitness</span>Buddy
        </div>

        <form class="form-inline" method="post" action="/controller">
            <button type="submit" class="btn btn-link btn-sm" name="command" value="en">en</button>
            <button type="submit" class="btn btn-link btn-sm" name="command" value="ua">ua</button>
        </form>
    </nav>
</div>


<div class="container">
    <form class="text-center" method="post" action="/controller">
        <h4 class="alert-danger">${registrationSuccessMessage}</h4>
        <h4 class="border-danger">${errorLoginPassMessage}</h4>
        <h4 class="border-danger">${userBlockMessage}</h4>
        <h2>Track meals. Stay committed.</h2>
        <br/>
        <div class="form-row align-items-center justify-content-center">
            <div class="col col-lg-3">
                <input
                        type="text"
                        name="login"
                        class="form-control <c:if test="${not empty errorLoginPassMessage}">alert-danger</c:if>"
                        value="${login}"

                        placeholder="login">
            </div>
            <div class="col col-lg-3">
                <input
                        type="password"
                        class="form-control <c:if test="${not empty errorLoginPassMessage}">border-danger</c:if>"
                        name="password"
                        value="${password}"
                        placeholder="password">
            </div>
        </div>
        <br>
        <div class="form-row align-items-center justify-content-center">
            <div class="col col-lg-3">
                <button type="submit"  name="command" value="LOGIN">Login</button>
            </div>
        </div>

        <div class="text-center">
            <label for="registration-btn">Not a member?</label>
            <button id="registration-btn" type="submit" class="btn btn-link justify-content-center" name="command"
                    value="to_registration_page">Register
            </button>
        </div>

    </form>

</div>

</body>
</html>
