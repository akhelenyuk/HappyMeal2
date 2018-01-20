<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="">
    <nav class="admin navbar justify-content-center">
        <span class="navbar-brand">Activity Diary</span>
    </nav>
    <br/>
    <c:forEach var="entry" items="${meals}">
        <c:if test="${not empty entry.value}">

            <table class="table table-sm">
                <div class="col-12 bg-light food-row-meal-title">${entry.key}</div>
                <thead class="text-right food-row-thead">
                <th scope="col"></th>
                <th scope="col">DURATION (MINS)</th>
                <th scope="col">CALORIES BURNT</th>
                <th scope="col"></th>
                <th scope="col"></th>
                </thead>

                <tbody class="text-right">
                <c:forEach var="mealEntry" items="${entry.value}">
                    <tr class="food-row-tr">
                        <td class="food_table_first_col_width text-left">${mealEntry.product}</td>
                        <td>${mealEntry.weight}</td>
                        <td>${mealEntry.calories}</td>
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
                    <td>${totalsByMealType[entry.key].weight}</td>
                    <td>${totalsByMealType[entry.key].calories}</td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>

            <table class="table table-sm">
                <div class="col-12 bg-light food-row-meal-title"></div>
                <thead class="text-right food-row-thead">
                <td class="food_table_first_col_width text-left"></td>
                <th class="invisible" scope="col">DURATION (MINS)</th>
                <th class="invisible" scope="col">CALORIES BURNT</th>
                <th class="invisible" scope="col"></th>
                <th class="invisible" scope="col"></th>
                </thead>

                <tbody class="text-right">

                <tr class="food-row-tr-bottom-final">
                    <th>Week total:</th>
                    <th>${totalDayFoodWeight}</th>
                    <th>${totalDayCalories}</th>
                        <%--TODO this is for table correct borders only. Buttons are invisible--%>
                    <td>
                        <button class="btn btn-link btn-block invisible food-row-tr-btn btn-sm" type="button">Edit
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-link btn-block invisible food-row-tr-btn btn-sm" type="button">Delete
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:if>

    </c:forEach>
</div>



