<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello, ${username}, !!!!!
<c:forEach var="customer" items="${customers}">
    <p>${customer}</p>
</c:forEach>
</body>
</html>
