<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${items}" var="item">
        <a href="${item.commandName}">${item.commandName}</a>
        <a>${item.description}</a><br>
    </c:forEach>
</body>
</html>
