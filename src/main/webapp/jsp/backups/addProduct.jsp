<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Add product</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col col-4">
            <div class="panel panel-default">
                <c:if test="${not empty errorProductExistMessage}"><h6
                        class="alert-danger">${errorProductExistMessage}</h6
></c:if>
                <div class="panel-heading">
                    <h3 class="panel-title">Add product</h3>
                </div>
                <div class="panel-body">

                    <form name="addProductForm" method="POST" action="controller">
                        <%--<input type="hidden" name="command" value="add_Product"/>--%>
                        <%--product name--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Name:</label>
                                </div>
                            </div>
                            <div class="col-xs-9 col-sm-9 col-md-9">
                                <div class="form-group">
                                    <input type="text"
                                           required
                                           name="name"
                                           class="form-control input-sm <c:if test="${not empty errorProductExistMessage}">border-danger</c:if>"
                                           value="${newProduct.name}"
                                           placeholder="Название">
                                </div>
                            </div>
                        </div>
                        <%--calories--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Calories:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number"
                                           required
                                           min="0"
                                           max="999"
                                           step="1"
                                           name="calories"
                                           value="${newProduct.calories}"
                                           class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <%--proteins--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Protein:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number"
                                           required
                                           min="0"
                                           max="99"
                                           step="0.1"
                                           name="protein"
                                           value="${newProduct.protein}"
                                           class="form-control input-sm">
                                </div>
                            </div>

                        </div>
                        <%--fat--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Fat:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number"
                                           required
                                           min="0"
                                           max="99"
                                           step="0.1"
                                           name="fat"
                                           value="${newProduct.fat}"
                                           class="form-control input-sm">
                                </div>
                            </div>

                        </div>
                        <%--carbs--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Carbs:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number"
                                           required
                                           min="0"
                                           max="99"
                                           step="0.1"
                                           name="carbs"
                                           value="${newProduct.carbs}"
                                           class="form-control input-sm">
                                </div>
                            </div>

                        </div>
                        <%--note (in 100 gramms...)--%>
                        <div class="row">
                            <label>* numbers are indicated in 100 grams of the product</label>
                        </div>

                        <%--buttons (Add Cancel)--%>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <button type="submit" name="command" value="ADD_NEW_PRODUCT"
                                            class="btn btn-success btn-block">Add product</button>

                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <button type="submit" formnovalidate name="command" value="Cancel"
                                            class="btn btn-default btn-block">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
