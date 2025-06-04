<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.jiralite.model.Bug" %>
<html>
<head><title>Edit Bug</title></head>
<body>
<h2>Edit Bug</h2>
<%
    Bug bug = (Bug) request.getAttribute("bug");
    if (bug != null) {
%>
<form action="update-bug" method="post">
    <input type="hidden" name="id" value="<%= bug.getId() %>" />
    Title: <input type="text" name="title" value="<%= bug.getTitle() %>" /><br/>
    Description: <textarea name="description"><%= bug.getDescription() %></textarea><br/>
    Assigned To: <input type="text" name="assigned_to" value="<%= bug.getAssignedTo() %>" /><br/>
    Status:
    <select name="status">
        <option value="Open" <%= "Open".equals(bug.getStatus()) ? "selected" : "" %>>Open</option>
        <option value="In Progress" <%= "In Progress".equals(bug.getStatus()) ? "selected" : "" %>>In Progress</option>
        <option value="Closed" <%= "Closed".equals(bug.getStatus()) ? "selected" : "" %>>Closed</option>
    </select><br/>
    <input type="submit" value="Update Bug" />
</form>
<% } else { %>
    <p>Bug not found.</p>
<% } %>
</body>
</html>
