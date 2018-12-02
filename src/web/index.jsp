<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 29.11.2018
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="Authorization" method="post">
        <input type="hidden" name="command" value="forward" />
        Enter login:<br/>
        <input type="text" name="login" value=""/><br/>
        Enter password:<br/>
        <input type="password" name="password" value="" /><br/>
        <input type="submit" value="OK" /><br/>
    </form>

</body>
</html>
