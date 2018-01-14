<nav class="navbar navbar-header">
    <form action="/controller" method="post">
        <button class="navbar-brand fitness-buddy btn btn-link" name="command" value="HOME"><span class="fitness">Fitness</span>Buddy
        </button>
    </form>
    <form class="form-inline" method="post" action="/controller">


        <c:if test="${not empty user}">
            <c:if test="${user.admin}">
                <button type="submit" class="btn btn-link" name="command" value="HOME">Home</button>
            </c:if>
            <%--<input type="submit" name="<command>" value="TO_LOGIN_PAGE">--%>
            <button type="submit" class="btn btn-link" name="command" value="LOGOUT">Logout</button>
        </c:if>
    </form>
</nav>