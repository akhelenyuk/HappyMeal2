<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home page</title>
    <jsp:include page="../pages/header.jsp"/>
    <link rel="stylesheet" href="/bootstrap/css/main.css">
</head>


<body>
<jsp:useBean id="userBean" class="com.khelenyuk.model.User"></jsp:useBean>
<jsp:useBean id="productBean" class="com.khelenyuk.model.Product"></jsp:useBean>
<%--<jsp:useBean id="menuBean" class="com.khelenyuk.model.Meal"></jsp:useBean>--%>
<%--<jsp:useBean id="localDate" class="java.time.LocalDate"></jsp:useBean>--%>

<c:set var="user" value="${userBean.user}"/>
<c:set var="products" value="${productBean.products}"/>
<%--<c:set var="menu" value="${menuBean.menu}"/>--%>
<%--<c:set var="chosenDateSession" value="${menu.date}"/>--%>

<jsp:include page="../pages/headerNavbar.jsp"></jsp:include>

<br/>
<div class="container">
    <%----------- JUMBOTRONs ------------%>
    <div class="row">
        <div class="col-4 jumbotron jumbotron1">
            <div class="hello">Hello, ${user.firstName}!</div>
            <div class="on_track">You are on track today!</div>
            <div class="still_to_go_text"><span class="still-to-go-number">15 kg </span>to your goal weight</div>
            <div class="still_to_go_text"><span class="still-to-go-number">500 calories </span>left to spend today</div>
            <div class="still_to_go_text"><span class="still-to-go-number">4 glasses </span> of water left to drink</div>
        </div>
        <div class="col-8 jumbotron jumbotron2 text-center">
            <div class="your-daily-summary">Your Daily Summary</div>
            <table class="table text-center borderless">
                <thead>
                <tr class="your-daily-summary-numbers your-daily-summary-table">
                    <th class="">1,900</th>
                    <th>-</th>
                    <th>900</th>
                    <th>+</th>
                    <th>500</th>
                    <th>=</th>
                    <th class="remaining">1,400</th>
                </tr>
                </thead>
                <tbody>
                <tr class="main-caption">
                    <td>GOAL</td>
                    <td></td>
                    <td>FOOD</td>
                    <td></td>
                    <td>EXERCISE</td>
                    <td></td>
                    <td>REMAINING</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>


    <div class="container">
        <%------------ TABS ----------------%>
        <ul class="nav nav-tabs nav-justified">
            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#food">Food</a>
            </li>
            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#exercise">Exercise</a>
            </li>
            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#water">Water</a>
            </li>
        </ul>

        <%--------------- TAB-CONTENT ------------%>
        <div class="tab-content table-bordered">
            <br/><br/>

            <%---------- FOOD -----------%>
            <div class="tab-pane active" id="food" role="tabpanel">
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
                                <label >Can't find a product?</label>
                                <button type="submit" class="btn btn-link font12">Add new</button></div>
                        </div>

                        <%-----------------   ENTER QUANTITY CONSUMED   ------------------%>
                        <div class="col">
                            <%---------- Enter product amount --------------%>
                            <div class="main-caption">ENTER QUANTITY CONSUMED (in grams)</div>
                            <input class="form-control" type="number" name="weight" step="1" min="1" value="100">

                        </div>
                    </div>
                    <br/>

                    <%-----------------  select meal number  -----------------%>
                    <div class="main-caption">SELECT MEAL</div>
                    <div class="row">
                        <div class="col col-2">
                            <%---------  breakfast  -------------%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="meal_number" id="radio-breakfast"
                                       value="1" checked>
                                <label class="form-check-label" for="radio-breakfast">Breakfast</label>
                            </div>

                            <%-------------  early lunch  --------------%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="meal_number" id="radio-early-lunch"
                                       value="2">
                                <label class="form-check-label" for="radio-early-lunch">Early lunch</label>

                            </div>
                        </div>
                        <div class="col col-2">
                            <%------------- lunch ------------%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="meal_number" id="radio-lunch"
                                       value="3">
                                <label class="form-check-label" for="radio-lunch">Lunch</label>
                            </div>

                            <%------------  early dinner  ------------------%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="meal_number" id="radio-early-dinner"
                                       value="4">
                                <label class="form-check-label" for="radio-early-dinner">Early dinner</label>
                            </div>
                        </div>
                        <div class="col col-2">
                            <%------------- dinner ------------%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="meal_number" id="radio-dinner"
                                       value="5">
                                <label class="form-check-label" for="radio-dinner">Dinner</label>
                            </div>

                            <%------------  late dinner  ------------------%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="meal_number" id="radio-late-dinner"
                                       value="6">
                                <label class="form-check-label" for="radio-late-dinner">Late dinner</label>
                            </div>
                        </div>
                    </div>
                    <br/><br/>


                    <%------------  BUTTON: add to diary  ---------%>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success" name="command" value="ADD_MEAL">Add to Food
                            Diary
                        </button>
                    </div>
                </form>

                <%-------------  SHOW FOOD DIARY  ------------%>
                <ul class="pager">
                    <li><a href="#">&lt</a> ${chosenDateSession} <a href="#">&gt</a></li>
                </ul>
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
                                <td>Total for a day:</td>
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

            <%------------ EXERCISE ---------------%>
            <div class="tab-pane" id="exercise" role="tabpanel">Exercise me</div>

            <%------------- WATER ------------%>
            <div class="tab-pane" id="water" role="tabpanel">Water</div>
        </div>
    </div>
</div>


</body>
</html>

<%----------   BUTTONs: FOOD, EXECRISE, WATER, STATS --------%>
<%--<nav class="navbar">--%>
<%--<form action="/servlet" method="post">--%>
<%--<button class="navbar-brand btn btn-secondary" name="command" value="HOME"><span--%>
<%--class="fitness">Food</span>--%>
<%--</button>--%>
<%--<button class="navbar-brand btn btn-secondary" name="command" value="Exercise"><span--%>
<%--class="fitness">Exercise</span>--%>
<%--</button>--%>
<%--<button class="navbar-brand btn btn-secondary " name="command" value="Water"><span--%>
<%--class="">Water</span>--%>
<%--</button>--%>
<%--<button class="navbar-brand btn btn-secondary" name="command" value="Statistics"><span--%>
<%--class="fitness">Statistics</span></button>--%>

<%--</form>--%>
<%--</nav>--%>



<%--<div class="row justify-content-center">--%>
<%--<button type="button" class="col-2.5 btn btn-info">Food</button>--%>
<%--<div class="col-1"></div>--%>
<%--<button type="button" class="col-2.5 btn btn-info">Exercise</button>--%>
<%--<div class="col-1"></div>--%>
<%--<button type="button" class="col-2.5 btn btn-info">Water</button>--%>
<%--&lt;%&ndash;<div class="col-1"></div>&ndash;%&gt;--%>
<%--<button type="button" class="col-2.5 btn btn-info">Statistic</button>--%>
<%--</div>--%>