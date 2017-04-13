<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FileTable</title>
</head>
<body>
<table border="1">
    <tbody>
    <caption><c:out value="${table.tableName}">${table.tableName}</c:out></caption>
    <tr>
        <c:forEach items="${table.tableData}" var="columns">
            <td>
                <c:out value="${columns.columnName}">${columns.columnName}</c:out><br>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach items="${table.tableData}" var="columns">
            <td>
                <c:forEach items="${columns.value}" var="value">
                    ${value}<br>
                </c:forEach>
            </td>
        </c:forEach>
    </tr>
    </tbody>
</table>
<form action="fileTable" method="post">
    <table>
        <tr>
            <td>File Name</td>
            <td><input type="text" name="FileName"/></td>
        </tr>
        <tr>
            <td>Absolute Path</td>
            <td><input type="text" name="AbsolutePath"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
