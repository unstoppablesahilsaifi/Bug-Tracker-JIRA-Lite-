<%@ page import="com.jiralite.model.User" %>
<%@ page import="java.util.List" %>
<html>
<head><title>User Management</title></head>
<body>
<h2>User Management</h2>
<a href="add-user">Add User</a>
<table border="1">
<tr><th>ID</th><th>Username</th><th>Role</th><th>Actions</th></tr>
<% List<User> users = (List<User>)request.getAttribute("users");
   for (User user : users) { %>
<tr>
    <td><%= user.getId() %></td>
    <td><%= user.getUsername() %></td>
    <td><%= user.getRole() %></td>
    <td>
        <a href="edit-user?id=<%= user.getId() %>">Edit</a> |
        <a href="delete-user?id=<%= user.getId() %>">Delete</a>
    </td>
</tr>
<% } %>
</table>
</body>
</html>
