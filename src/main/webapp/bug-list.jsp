<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.jiralite.model.Bug, java.util.List" %>
<html>
<head><title>Bug List</title></head>
<body>
<h2>Bug List</h2>

<%-- Debug info: Print total bugs --%>
<%
    List<Bug> bugs = (List<Bug>) request.getAttribute("bugs");
    if (bugs != null) {
%>
<p>Total Bugs: <%= bugs.size() %></p>
<%
    }
%>

<table border="1">
<tr><th>ID</th><th>Title</th><th>Description</th><th>Status</th><th>Actions</th></tr>
<%
    if (bugs != null) {
        for (Bug bug : bugs) {
%>
<tr>
    <td><%= bug.getId() %></td>
    <td><%= bug.getTitle() %></td>
    <td><%= bug.getDescription() %></td>
    <td><%= bug.getStatus() %></td>
    <td><a href="edit-bug?id=<%= bug.getId() %>">Edit</a></td>
    <td><a href="delete-bug?id=<%= bug.getId() %>" onclick="return confirm('Are you sure you want to delete this bug?');">Delete</a>
</td>
</tr>
<%      }
    } else {
%>
<tr><td colspan="5">No bugs found.</td></tr>
<% } %>
</table>
</body>
</html>
