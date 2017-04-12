<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:choose>
        <c:when test="${manager == null}">
            <a href="connect">Connect</a>
            <a>     Команда для подключения к соответствующей БД</a><br>
        </c:when>
        <c:otherwise>
            <c:forEach items="${items}" var="item">
                <a href="${item.commandName}">${item.commandName}</a>
                <a>${item.description}</a><br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
