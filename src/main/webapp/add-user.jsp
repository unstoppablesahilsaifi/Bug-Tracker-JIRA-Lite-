<html>
<head><title>Add User</title></head>
<body>
<h2>Add User</h2>
<form method="post" action="add-user">
    Username: <input type="text" name="username" required /><br/>
    Password: <input type="password" name="password" required /><br/>
    Role: <select name="role">
        <option value="admin">Admin</option>
        <option value="developer">Developer</option>
        <option value="tester">Tester</option>
    </select><br/>
    <input type="submit" value="Add User" />
</form>
</body>
</html>
