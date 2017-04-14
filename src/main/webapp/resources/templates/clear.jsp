<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Clear</title>
</head>
<body>
<form action="clear" method="post">
    <table>
        <tr>
            <td>Table Name</td>
            <td><input type="text" name="TableName"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Очистить"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
