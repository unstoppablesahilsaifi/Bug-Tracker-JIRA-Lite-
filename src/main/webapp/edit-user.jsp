<%@ page import="com.jiralite.model.User" %>
<% User user = (User)request.getAttribute("user"); %>
<html>
<head><title>Edit User</title></head>
<body>
<h2>Edit User</h2>
<form method="post" action="edit-user">
    <input type="hidden" name="id" value="<%= user.getId() %>" />
    Username: <input type="text" name="username" value="<%= user.getUsername() %>" required /><br/>
    Password: <input type="password" name="password" value="<%= user.getPassword() %>" required /><br/>
    Role: <select name="role">
        <option value="admin" <%= user.getRole().equals("admin") ? "selected" : "" %>>Admin</option>
        <option value="developer" <%= user.getRole().equals("developer") ? "selected" : "" %>>Developer</option>
        <option value="tester" <%= user.getRole().equals("tester") ? "selected" : "" %>>Tester</option>
    </select><br/>
    <input type="submit" value="Update User" />
</form>
</body>
</html>
