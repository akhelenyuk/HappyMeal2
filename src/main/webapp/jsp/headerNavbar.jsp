<nav class="navbar">
    <form action="/" method="post">
        <button class="navbar-brand fitness-buddy btn btn-link" name="command" value="home"><span class="fitness">Fitness</span>Buddy</button>
    </form>
    <form class="form-inline" method="post" action="/">


        <c:if test="${not empty user}">
            <c:if test="${user.admin}">
                <button type="submit" class="btn btn-link" name="command" value="Home">Home</button>
            </c:if>
            <button type="submit" class="btn btn-link" value="Logout">Logout</button>
        </c:if>
    </form>
</nav>