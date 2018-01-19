<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="">
    <nav class="admin navbar justify-content-center">
        <span class="navbar-brand">Food Diary</span>
    </nav>
    <br/>

    <c:forEach var="entry" items="${meals}">
        <c:if test="${not empty entry.value}">
            <table class="table table-sm">
                <div class="col-12 bg-light food-row-meal-title">${entry.key}</div>
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
                <c:forEach var="mealEntry" items="${entry.value}">
                    <tr class="food-row-tr">
                        <td class="food_table_first_col_width text-left">${mealEntry.product}</td>
                        <td>${mealEntry.weight}</td>
                        <td>${mealEntry.calories}</td>
                        <td>${mealEntry.protein}</td>
                        <td>${mealEntry.fat}</td>
                        <td>${mealEntry.carbs}</td>
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
        </c:if>

    </c:forEach>
</div>


<table class="table table-sm">
    <div class="col-12 bg-light food-row-meal-title"></div>
    <thead class="text-right food-row-thead">
    <td class="food_table_first_col_width text-left"></td>
    <th class="invisible"scope="col">WEIGHT</th>
    <th class="invisible" scope="col">CALORIES</th>
    <th class="invisible"scope="col">PROTEINS</th>
    <th class="invisible" scope="col">FATS</th>
    <th class="invisible" scope="col">CARBS</th>
    <th class="invisible" scope="col"></th>
    <th class="invisible"scope="col"></th>
    </thead>

    <tbody class="text-right">

    <tr class="food-row-tr-bottom-final">
        <th>Total for the day:</th>
        <th>${totalDayFoodWeight}</th>
        <th>${totalDayCalories}</th>
        <th>${totalDayProteins}</th>
        <th>${totalDayFat}</th>
        <th>${totalDayCarbs}</th>
        <%--TODO this is for table correct borders only. Buttons are invisible--%>
        <td>
            <button class="btn btn-link btn-block invisible food-row-tr-btn btn-sm" type="button">Edit</button>
        </td>
        <td>
            <button class="btn btn-link btn-block invisible food-row-tr-btn btn-sm" type="button">Delete</button>
        </td>
    </tr>
    </tbody>
</table>


<%--<c:forEach var="product" items="${productList}" begin="1" end="4">--%>
<%--<tr class="food-row-tr">--%>
<%--<td class="food_table_first_col_width text-left">${product.name}</td>--%>
<%--<td>100</td>--%>
<%--<td>${product.calories}</td>--%>
<%--<td>${product.protein}</td>--%>
<%--<td>${product.fat}</td>--%>
<%--<td>${product.carbs}</td>--%>
<%--<td class="align-right">--%>
<%--<button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Edit</button>--%>
<%--</td>--%>
<%--<td>--%>
<%--<button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Delete</button>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--<tr class="food-row-tr-bottom">--%>
<%--<td>Total:</td>--%>
<%--<td>100</td>--%>
<%--<td>100</td>--%>
<%--<td>100</td>--%>
<%--<td>100</td>--%>
<%--<td>100</td>--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--</tr>--%>
<%--<tr class="food-row-tr-bottom">--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--<td></td>--%>
<%--</tr>--%>