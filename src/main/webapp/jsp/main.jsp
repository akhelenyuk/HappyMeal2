<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home page</title>
    <jsp:include page="header.jsp"/>
    <link rel="stylesheet" href="/bootstrap/css/main.css">
    <link rel="stylesheet" href="/bootstrap/css/adminpage.css">
</head>


<body class="bg">

<div class="container bg-container">
    <jsp:include page="headerNavbar.jsp"></jsp:include>

    <br/>
    <%----------- JUMBOTRONs ------------%>

    <div class="container container-fluid table-bordered">
        <div class="row">
            <div class="col-4 jumbotron jumbotron1">
                <div class="hello">Hello, ${user.firstName}!</div>
                <div class="on_track">
                    ${remaining > 0 ? 'You are on track today!' : ''}</div>
                <div class="still_to_go_text">
                    <c:choose>
                        <c:when test="${kgToGoal > 0}">
                            <span class="still-to-go-number">${kgToGoal} kg </span>to your goal weight
                        </c:when>
                        <c:otherwise>
                            <span class="still-to-go-number">Well done! You have reached your weight goal!</span>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="still_to_go_text">
                    <c:choose>
                        <c:when test="${remaining > 0}">
                            <span class="still-to-go-number">${remaining} calories </span>left to spend today
                        </c:when>
                        <c:otherwise>
                            <span class="still-to-go-number-minus">You are exceeding calories consumption by ${remaining} calories. Do more activities!</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <%--<div class="still_to_go_text"><span class="still-to-go-number">4 glasses </span> of water left to drink</div>--%>
            </div>
            <div class="col jumbotron jumbotron2 text-center">
                <div class="your-daily-summary">Your Daily Summary</div>
                <table class="table text-center borderless">
                    <thead>
                    <tr class="your-daily-summary-numbers your-daily-summary-table">
                        <th class="">${user.calorieNorm}</th>
                        <th>-</th>
                        <th>${totalDayCalories}</th>
                        <th>+</th>
                        <th>${activitiesListTotals.calories}</th>
                        <th>=</th>
                        <th class="${remaining < 0 ? 'remaining-minus' : 'remaining'}">
                            ${remaining}
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="main-caption">
                        <td>GOAL</td>
                        <td></td>
                        <td>FOOD</td>
                        <td></td>
                        <td>ACTIVITY</td>
                        <td></td>
                        <td>
                            ${remaining < 0 ? 'EXCEEDING' : 'REMAINING'}
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div>
                    <form name="calendarForm" method="post" action="controller">
                        <input type="hidden" name="command" value="Select_date">
                        <input type="date" name="chosenDate" value="${chosenDateSession}"/>

                        <input type="submit" class="button" value="Select Date">
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="container table-bordered">

        <ul class="nav nav-tabs nav-justified">
            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#food">Food</a>
            </li>
            <li class="nav-item"><a class="nav-link " role="tab" data-toggle="tab" href="#activity">Activity</a>
            </li>
            <%--<li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#water">Water</a>--%>
            <%--</li>--%>
            <li class="nav-item"><a class="nav-link " role="tab" data-toggle="tab" href="#bodyStats">Body
                stats</a>
            </li>
        </ul>


        <div class="tab-content table-bordered">
            <br/><br/>

            <%---------- FOOD -----------%>
            <div class="tab-pane active" id="food" role="tabpanel">


                <%---------- FOOD -----------%>
                <div class="container container-fluid table-bordered">
                    <form action="/controller" method="post">


                        <%---------------   select product  ---------------%>
                        <div class="row ">
                            <%----------------   SELECT PRODUCT   -----------------%>
                            <div class="col">
                                <div class="main-caption">SELECT PRODUCT</div>

                                <%------------  Choose product ------------%>
                                <select class="form-control" name="product_id">
                                    <c:forEach var="product" items="${products}">
                                        <option value="${product.id}">
                                            <c:out value="${product.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>

                                <%------------- Add New product ------------%>
                                <div class="form-inline font12">
                                    <label>Can't find a product?</label>
                                    <button type="submit" class="btn btn-link font12" name="command"
                                            value="TO_ADD_PRODUCT_PAGE">Add new
                                    </button>
                                </div>
                            </div>

                            <%-----------------   ENTER QUANTITY CONSUMED   ------------------%>
                            <div class="col">
                                <%---------- Enter product amount --------------%>
                                <div class="main-caption">ENTER QUANTITY (grams)</div>
                                <input class="form-control" type="number" name="weight" step="1" min="1" max="999"
                                       value="100">

                            </div>

                            <div class="col">
                                <div class="main-caption">SELECT MEAL</div>

                                <%------------  Choose meal type ------------%>
                                <select class="form-control" name="meal_type_id">
                                    <c:forEach var="meal_type" items="${mealTypes}">
                                        <option value="${meal_type.id}">
                                            <c:out value="${meal_type.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%------------  BUTTON: add to diary  ---------%>
                        <div class="text-center">
                            <button type="submit" class="btn btn-success" name="command" value="ADD_MEAL">Add to Food
                                Diary
                            </button>
                        </div>
                    </form>
                </div>
                <br>
                <%-------------  SHOW FOOD DIARY  ------------%>
                <%--meal--%>
                <c:if test="${not empty meals}">
                    <jsp:include page="includeFoodTab.jsp"/>
                </c:if>
            </div>

            <%------------ activity ---------------%>
            <div class="tab-pane " id="activity" role="tabpanel">
                <jsp:include page="includeAddActivityBlock.jsp"/>
                <c:if test="${not empty activitiesList}">
                    <jsp:include page="includeActivityTable.jsp"/>
                </c:if>
            </div>

            <%--&lt;%&ndash;----------- WATER ----------&ndash;%&gt;--%>
            <%--<div class="tab-pane" id="water" role="tabpanel">Water</div>--%>

            <%------------- Weight monitor ------------%>
            <div class="tab-pane " id="bodyStats" role="tabpanel">
                <jsp:include page="includeAddBodyStatsBlock.jsp"/>
            </div>
        </div>

    </div>


</body>
</html>

