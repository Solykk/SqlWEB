<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<form action="Delete" method="post">
    <table>
        <tr>
            <td>Table Name</td>
            <td><input type="text" name="TableName"/></td>
        </tr>
    </table>

    <table>
        <c:forEach items="${inputVal}" var="inputs">
            <c:choose>
                <c:when test="${inputs%2 == 0}">
                    <tr>
                        <td>Column Name</td>
                        <td><input type="text" name="settings[]" /></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>Value</td>
                        <td><input type="text" name="settings[]" /></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <tr>
            <td></td>
            <td><input type="submit" value="Добавить поле" name="add"/></td>
        </tr>
    </table>
    <table>
        <tr>
            <td></td>
            <td><input type="submit" value="Очистить" name="put"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
