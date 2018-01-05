<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Add product</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Добавить продукт</h3>
                </div>
                <div class="panel-body">
                    <form name="addProductForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="add_Product" />
                        <%--product name--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Название:</label>
                                </div>
                            </div>
                            <div class="col-xs-9 col-sm-9 col-md-9">
                                <div class="form-group">
                                    <input type="text" required name="name" class="form-control input-sm" placeholder="Название">
                                </div>
                            </div>
                        </div>
                        <%--calories--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Калории:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number" required min="0" max="999" step="1" name="calories" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <%--proteins--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Белки:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number" required min="0" max="99" step="0.1" name="protein" class="form-control input-sm">
                                </div>
                            </div>

                        </div>
                        <%--fat--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Жиры:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number" required min="0" max="99" step="0.1" name="fat" class="form-control input-sm">
                                </div>
                            </div>

                        </div>
                        <%--carbs--%>
                        <div class="row">
                            <div class="col-xs-3 col-sm-3 col-md-3">
                                <div class="form-group">
                                    <label>Углеводы:</label>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4 col-md-4">
                                <div class="form-group">
                                    <input type="number" required min="0" max="99" step="0.1" name="carbs" class="form-control input-sm">
                                </div>
                            </div>

                        </div>
                        <%--note (in 100 gramms...)--%>
                        <div class="row">
                            <label>* показатели указываются в 100 граммах продукта</label>
                        </div>

                        <%--buttons (Add Cancel)--%>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="submit" name="button" value="Добавить"
                                           class="btn btn-success btn-block">

                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="submit" name="button" value="Отменить"
                                           class="btn btn-default btn-block">
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
