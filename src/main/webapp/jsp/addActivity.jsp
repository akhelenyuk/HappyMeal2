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
                <%--TODO change to activity message--%>
                <c:if test="${not empty errorActivityExistMessage}"><h6
                        class="alert-danger">${errorActivityExistMessage}</h6
                ></c:if>
                <div class="panel-heading text-center">
                    <h3 class="panel-title">Add activity2</h3>
                </div>
                <div class="panel-body">

                    <form method="POST" action="controller">

                        <div class="row">
                            <div class="col-lg-3 form-group">
                                <label>Name:</label>
                            </div>
                            <div class="col form-group">
                                <input type="text"
                                       required
                                       name="name"
                                       class="form-control input-sm <c:if test="${not empty errorActivityExistMessage}">border-danger</c:if>"
                                       value="${newActivity.name}"
                                       placeholder="Activity">
                            </div>
                        </div>
                        <%--calories--%>
                        <div class="row">
                            <div class="col-lg-3 form-group">
                                <label>Calories:</label>
                            </div>
                            <div class="col form-group">
                                <input type="number"
                                       required
                                       min="1"
                                       max="999"
                                       step="1"
                                       name="calories"
                                       value="${newActivity.calories}"
                                       class="form-control input-sm">
                            </div>
                        </div>

                        <%--note (in 100 gramms...)--%>
                        <div class="row">
                            <label>* calories are indicated for 60 minutes of activity</label>
                        </div>

                        <%--buttons (Add Cancel)--%>
                        <div class="row">
                            <div class="col form-group">
                                <button type="submit" name="command" value="ADD_NEW_ACTIVITY"
                                        class="btn btn-success btn-block">Add activity
                                </button>
                            </div>
                            <div class="col">
                                    <button type="submit" formnovalidate name="command" value="Cancel"
                                            class="btn btn-default btn-block">Cancel
                                    </button>
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
