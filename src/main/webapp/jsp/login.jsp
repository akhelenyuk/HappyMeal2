<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="loginpagecontent"/>

<html>
<head>
    <title><fmt:message key="label.title"/></title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap/css/main.css">
</head>
<body>

<div class="container">
    <div align="right">
        <form name="localeForm" method="post" action="controller">
            <input type="hidden" name="command" value="set_Locale"/>

            <select name="locale">
                <option value="EN" ${selectedLocale == "EN"? 'selected="selected"':''}>english</option>
                <option value="RU" ${selectedLocale == "RU" ? 'selected="selected"':''}>русский</option>
                <option value="UA" ${selectedLocale == "UA" ? 'selected="selected"':''}>українська</option>
            </select>
            <input type="submit" value="<fmt:message key="label.setlanguage"/>"/>
            <br/>
        </form>
    </div>


    <div align="center">
        <form name="loginForm" method="POST" action="controller">

            <input type="hidden" name="command" value="login"/>
            <fmt:message key="label.login"/>:<br/>
            <input type="text" name="login" value=""/>
            <br/><fmt:message key="label.password"/>:<br/>
            <input type="password" name="password" value=""/>
            <br/><br/>
            <input type="submit" value="<fmt:message key="label.loginbutton"/>"/>
        </form>

        <form name="registrationForm" method="post" action="controller">
            <input type="hidden" name="command" value="to_registration_page">
            <input type="submit" class="alert-success" value="<fmt:message key="label.registrationbutton"/>"/>
        </form>

        <div>
            ${registrationSuccessMessage}
            ${errorLoginPassMessage}
            ${wrongAction}
            ${nullPage}
        </div>
    </div>
</div>
</body>
</html>