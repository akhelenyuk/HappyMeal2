<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="/bootstrap/css/mainpage.css">--%>
    <%--<link rel="stylesheet" href="/bootstrap/css/header.css">--%>

    <%--<link rel="script" href="/bootstrap/js/bootstrap.min.js">--%>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


</head>

<body>

<div class="container">



    <%--Tabs: sport, water, stats--%>
    <ul class="nav nav-tabs">
        <li class="nav-item"><a class="nav-link active" data-toggle="tab" role="tab" href="#food">Food</a></li>
        <li class="nav-item"><a class="nav-link" data-toggle="tab" role="tab" href="#exercise">Exercise</a></li>
        <li class="nav-item"><a class="nav-link" data-toggle="tab" role="tab" href="#water">Water</a></li>
        <li class="nav-item"><a class="nav-link" data-toggle="tab" role="tab" href="#stats">Statistics</a></li>
    </ul>

    <div class="tab-content">
        <div id="food" class="tab-pane active" role="tabpanel">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="add_meal"/>
                <input type="hidden" name="user_id" value="${user.id}"/>
                <%--<input type="hidden" name="chosenDateHidden" value="${test2}"/>--%>


                <%--Choose product--%>
                <div class="row well">
                    <h3>Select product</h3>
                    <p>
                        <%--Choose product--%>
                        <select name="product_id">
                            <c:forEach var="product" items="${products}">
                                <option value="${product.id}">
                                    <c:out value="${product.name}"/>
                                </option>
                            </c:forEach>
                        </select>

                        <%--enter amount--%>
                        <label>Amount consumed</label>
                        <input type="number" name="weight" step="1" min="1" value="100">
                        <label> grammes </label>
                    </p>
                </div>

                <div>
                    <h6>Can't find a product?
                        <a href="addProduct.jsp">Add new</a></h6>
                </div>

                <%--choose meal--%>
                <h3>Select meal</h3>
                <div class="row well">
                    <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
                        <div><label><input name="meal_number" value="1" type="radio" checked>Breakfast</label></div>
                        <div><label><input name="meal_number" value="2" type="radio">Early lunch</label></div>
                        <div><label><input name="meal_number" value="3" type="radio">Lunch</label></div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
                        <div><label><input name="meal_number" value="4" type="radio">Early Dinner</label></div>
                        <div><label><input name="meal_number" value="5" type="radio">Dinner</label></div>
                        <div><label><input name="meal_number" value="6" type="radio">Late Dinner</label></div>
                    </div>
                </div>

                <div>
                    <input type="submit" value="Add to Food Diary">
                </div>

            </form>

            <%--menu--%>
            <c:if test="${not empty menu}">
                <div class="row well">

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>AMOUNT</th>
                            <th>CALORIES</th>
                            <th>PROTEIN</th>
                            <th>FAT</th>
                            <th>CARBOHYDRATE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="meal" items="${menu}">
                            <tr>
                                <td><c:out value="${meal.mealNumber}"></c:out></td>
                                <td><c:out value="${meal.product}"></c:out></td>
                                <td><c:out value="${meal.weight}"></c:out></td>
                                <td><c:out value="${meal.calories}"></c:out></td>
                                <td><c:out value="${meal.protein}"></c:out></td>
                                <td><c:out value="${meal.fat}"></c:out></td>
                                <td><c:out value="${meal.carbs}"></c:out></td>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tbody>
                        <tr>
                            <td></td>
                            <td>Итого за день:</td>
                            <td><c:out value="${userTotalWeight}"></c:out></td>
                            <td><c:out value="${userTotalCalories}"></c:out></td>
                            <td><c:out value="${userTotalProteins}"></c:out></td>
                            <td><c:out value="${userTotalFat}"></c:out></td>
                            <td><c:out value="${userTotalCarbs}"></c:out></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>Норма:</td>
                            <td></td>
                            <td><c:out value="${user.calorieNorm}"></c:out></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
        <div id="exercise" class="tab-pane fade">
            <h3>Exercise</h3>
        </div>
        <div id="water" class="tab-pane fade">
            <h3>Water</h3>
        </div>
        <div id="stats" class="tab-pane fade">
            <h3>Statistics</h3>
        </div>

</div>

</div>
</body>
</html>
