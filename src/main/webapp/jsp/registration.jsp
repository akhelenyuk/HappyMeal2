<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap/css/main.css">

</head>
<body>
<%--<jsp:useBean id="registrationUser" class="com.khelenyuk.model.User"/>--%>
<div class="container">
    <div align="center">
        <form action="/controller" method="post">
            <c:if test="${not empty errorLoginExistMessage}"><h4
                    class="alert-danger">${errorLoginExistMessage}</h4></c:if>
            <h3>Sign Up</h3>
            <h6>Already have a FitnessBuddy acount?
                <button type="submit" class="btn btn-link" name="command" value="TO_LOGIN_PAGE">Log in</button>
            </h6>
        </form>
        <form name="registrationForm" action="/controller" method="post">
            <input type="text"
                   required
                   name="first_name"
                   value="${registrationUser.firstName}"
                   placeholder="First name">
            <input type="text"
                   required
                   name="last_name"
                   value="${registrationUser.lastName}"
                   placeholder="Last name"><br/>
            <input type="text"
                   required
                   name="login"
                   value="${registrationUser.login}"
                   <c:if test="${not empty errorLoginExistMessage}">class="border-danger"</c:if>
                   placeholder="Login">

            <input type="email" value="${registrationUser.email}" name="email" placeholder="e-mail"><br/>
            <input type="password" required name="password" placeholder="Пароль">
            <input type="password" required name="password_confirmation" placeholder="Подтвердите пароль">
            <label class="alert-danger">${errorPassConfirmMessage}</label>
            <div><br/><br/></div>

            <div>
                <label>Дата рождения:</label><input type="date" name="birthday"
                                                    value="${registrationUser.birthday}"><br/>
                <input type="number" name="weight" min="0" max="300" step="0.1" value="${registrationUser.weight}"
                       placeholder="Вес(кг)">
                <input type="number" name="height" min="0" max="250" step="0.1" value="${registrationUser.height}"
                       placeholder="Рост(см)"><br/>
            </div>
            <br/>

            <label>Укажите ваш пол:</label>
            <select name="sex">
                <c:forEach var="item" items="${sex}">
                    <option value="${item.id}">
                        <c:out value="${item.name}"></c:out>
                    </option>
                </c:forEach>
            </select><br/>

            <label>Укажите вашу активность:</label>

            <select name="lifestyle">
                <c:forEach var="item" items="${lifestyle}">
                    <option value="${item.id}">
                        <c:out value="${item.name}"></c:out>
                    </option>
                </c:forEach>
            </select>
            <div><br/><br/></div>

            <button type="submit" name="command" class="btn" value="REGISTER_NEW_USER">Register</button>
            <%--TODO implement "to previous page" functionality --%>
            <button type="submit" formnovalidate name="command" class="btn" value="TO_LOGIN_PAGE">Cancel</button>
        </form>

        <div>${errorRegistrationMessage}</div>
    </div>
</div>

</body>
</html>
