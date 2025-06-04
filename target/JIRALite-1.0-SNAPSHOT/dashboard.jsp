<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Welcome, ${username}!</h1>
<p>Your role: ${role}</p>

<c:choose>
    <c:when test="${role == 'Admin'}">
        <p><a href="admin.jsp">Go to Admin Panel</a></p>
    </c:when>
    <c:when test="${role == 'Tester'}">
        <p><a href="reportBug.jsp">Report a New Bug</a></p>
    </c:when>
    <c:when test="${role == 'Developer'}">
        <p><a href="assignedBugs.jsp">View Assigned Bugs</a></p>
    </c:when>
</c:choose>

<p><a href="logout">Logout</a></p>
