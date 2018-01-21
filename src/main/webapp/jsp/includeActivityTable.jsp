<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="">
    <nav class="admin navbar justify-content-center">
        <span class="navbar-brand">Activity Diary</span>
    </nav>
    <br/>

    <table class="table table-sm">
        <div class="col-12 bg-light food-row-meal-title">${chosenDateSession}</div>
        <thead class="text-right food-row-thead">
        <th scope="col"></th>
        <th scope="col">DURATION (MINS)</th>
        <th scope="col">CALORIES BURNT</th>
        <th scope="col"></th>
        <th scope="col"></th>
        </thead>

        <tbody class="text-right">
        <c:forEach var="entry" items="${activitiesList}">
            <tr class="food-row-tr">
                <td class="food_table_first_col_width text-left">${entry.activity}</td>
                <td>${entry.timeSpent}</td>
                <td>${entry.calories}</td>
                <td class="align-right">
                    <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Edit</button>
                </td>
                <td>
                    <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit">Delete</button>
                </td>
            </tr>
        </c:forEach>

        <tr class="food-row-tr-bottom-final">
            <td>Total:</td>
            <td>${activitiesListTotals.timeSpent}</td>
            <td>${activitiesListTotals.calories}</td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>


</div>



