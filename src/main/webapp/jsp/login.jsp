<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="loginpagecontent"/>

<html>
<head>
    <title><fmt:message key="label.title"/></title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap/css/login.css">
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

<h4>${registrationSuccessMessage}</h4>
<div class="container">
    <form class="text-center" method="get" action="/controller">
        <h2>Track meals. Stay committed.</h2>
        <br/>
        <div class="form-row align-items-center justify-content-center">
            <div class="col col-lg-3">
                <input type="text" name="username" class="form-control " placeholder="login">
            </div>
            <div class="col col-lg-3">
                <input type="text" class="form-control" name="password" placeholder="password">
            </div>
        </div>
        <br>
        <div class="form-row align-items-center justify-content-center">
            <div class="col col-lg-3">
                <button type="submit" name="command" value="TO_LOGIN_PAGE">Login</button>
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