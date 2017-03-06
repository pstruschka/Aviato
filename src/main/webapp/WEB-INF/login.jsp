<%--
  Created by IntelliJ IDEA.
  User: DevSingh
  Date: 3/6/17
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<h2>Register</h2>
    <form action="/login" method="post">
        Username:<br/>
        <input type="text" name="username"/>
        <br/>
        Password:<br/>
        <input type="password" name="password">
        <br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
