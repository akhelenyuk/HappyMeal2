<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap/css/main.css">

</head>
<body>

<div class="container">
    <div align="center">
            <form name="registrationForm" action="controller" method="post">
                <input type="hidden" name="command" value="register_new_user"/>

                <div>
                    <h3>Регистрация нового пользователя</h3>
                </div>
                <input type="text" required name="first_name" placeholder="Имя">
                <input type="text" required name="last_name" placeholder="Фамилия"><br/>
                <input type="text" required name="login" placeholder="Логин">
                <label class="alert-danger">${errorLoginExistMessage}</label>
                <input type="email" name="email" placeholder="e-mail"><br/>
                <input type="password" required name="password" placeholder="Пароль">
                <input type="password" required name="password_confirmation" placeholder="Подтвердите пароль">
                <label class="alert-danger">${errorPassConfirmMessage}</label>
                <div><br/><br/></div>

                <div>
                    <label>Дата рождения:</label><input type="date" name="birthday"><br/>
                    <input type="number" name="weight" min="0" max="300" step="0.1" placeholder="Вес(кг)">
                    <input type="number" name="height" min="0" max="250" step="0.1" placeholder="Рост(см)"><br/>
                </div><br/>

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


                <input type="submit" class="button alert-success" value="Зарегистрироваться">
                <input type="submit" class="button alert-secondary" value="Отмена">

            </form>

        <div>${errorRegistrationMessage}</div>
    </div>
</div>

</body>
</html>
