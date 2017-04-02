<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ColumnType</title>
</head>
<body>
<form action="ColumnType" method="post">
    <table>
        <tr>
            <td>Table Name</td>
            <td><input type="text" name="TableName"/></td>
        </tr>
        <tr>
            <td>Column Name</td>
            <td><input type="text" name="ColumnName"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Найти"/></td>
        </tr>
    </table>
</form>
<c:choose>
    <c:when test="${table == null}">
    </c:when>
    <c:otherwise>
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
        <form action="FileTable" method="get">
            <table>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Сохранить в файл"/></td>
                </tr>
            </table>
        </form>
    </c:otherwise>
</c:choose>
<%@include file="footer.jsp" %>

</body>
</html>
