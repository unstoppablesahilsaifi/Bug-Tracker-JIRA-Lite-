<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>JIRALite - Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <label>Username:</label>
        <input type="text" name="username" value="${cookie.username.value}" required/><br/>
        <label>Password:</label>
        <input type="password" name="password" required/><br/>
        <label>Remember Me:</label>
        <input type="checkbox" name="remember" ${cookie.username.value != null ? "checked" : ""}/><br/>
        <input type="submit" value="Login"/>
    </form>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
</body>
</html>
