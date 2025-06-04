<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Submit Bug</title>
</head>
<body>
    <h2>Submit a Bug</h2>
    <form action="submit-bug" method="post">
        <label>Title:</label><br/>
        <input type="text" name="title" required/><br/><br/>
        <label>Description:</label><br/>
        <textarea name="description" required></textarea><br/><br/>
        <input type="submit" value="Submit Bug"/>
    </form>

    <c:if test="${not empty message}">
        <p style="color:green">${message}</p>
    </c:if>
</body>
</html>
