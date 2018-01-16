<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="userBean" class="com.khelenyuk.model.User"></jsp:useBean>
<jsp:useBean id="productBean" class="com.khelenyuk.model.Product"></jsp:useBean>
<c:set var="userList" value="${userBean.users}"/>
<c:set var="productList" value="${productBean.products}"/>
<c:set var="user1" value="${userBean.user}"/>


<div class="">
    <nav class="admin navbar justify-content-center">
        <span class="navbar-brand">Food Diary</span>
    </nav>
    <br/>


    <table class="table table-sm">
        <div class="col-12 bg-light food-row-meal-title">Breakfast</div>
        <thead class="text-right food-row-thead">
        <th scope="col"></th>
        <th scope="col">WEIGHT</th>
        <th scope="col">CALORIES</th>
        <th scope="col">PROTEINS</th>
        <th scope="col">FATS</th>
        <th scope="col">CARBS</th>
        <th scope="col"></th>
        <th scope="col"></th>
        </thead>

        <tbody class="text-right">
        <c:forEach var="product" items="${productList}" begin="1" end="4">
            <tr class="food-row-tr">
                <td class="food_table_first_col_width text-left">${product.name}</td>
                <td>100</td>
                <td>${product.calories}</td>
                <td>${product.protein}</td>
                <td>${product.fat}</td>
                <td>${product.carbs}</td>
                <td class="align-right">
                    <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Edit</button>
                </td>
                <td>
                    <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Delete</button>
                </td>
            </tr>
        </c:forEach>
        <tr class="food-row-tr-bottom">
            <td>Total:</td>
            <td>100</td>
            <td>100</td>
            <td>100</td>
            <td>100</td>
            <td>100</td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>

    <table class="table table-sm">
        <div class="col-12 bg-light food-row-meal-title">Lunch</div>
        <thead class="text-right food-row-thead">
        <th scope="col"></th>
        <th scope="col">WEIGHT</th>
        <th scope="col">CALORIES</th>
        <th scope="col">PROTEINS</th>
        <th scope="col">FATS</th>
        <th scope="col">CARBS</th>
        <th scope="col"></th>
        <th scope="col"></th>
        </thead>

        <tbody class="text-right">
        <c:forEach var="product" items="${productList}" begin="1" end="4">
            <tr class="food-row-tr">
                <td class="food_table_first_col_width text-left">${product.name}</td>
                <td>100</td>
                <td>${product.calories}</td>
                <td>${product.protein}</td>
                <td>${product.fat}</td>
                <td>${product.carbs}</td>
                <td class="align-right">
                    <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Edit</button>
                </td>
                <td>
                    <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Delete</button>
                </td>
            </tr>
        </c:forEach>
        <tr class="food-row-tr-bottom">
            <td>Total:</td>
            <td>100</td>
            <td>100</td>
            <td>100</td>
            <td>100</td>
            <td>100</td>
            <td></td>
            <td></td>
        </tr>
        <tr class="food-row-tr-bottom">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr class="food-row-tr-bottom-final">
            <th>Total for the day:</th>
            <th>2345</th>
            <th>3456</th>
            <th>100</th>
            <th>100</th>
            <th>256</th>
            <th></th>
            <th></th>
        </tr>
        </tbody>
    </table>

</div>

<%--</body>--%>
<%--</html>--%>