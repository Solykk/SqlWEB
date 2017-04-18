<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FindSettings</title>
</head>
<body>
<form action="fSettings" method="post">
    <table>
        <tr>
            <td>Table Name</td>
            <td><input type="text" name="TableName" value="${TableName}"/></td>
        </tr>
    </table>
<table>
        <c:forEach items="${inputVal}" var="inputs" >
            <c:choose>
                <c:when test="${inputs%2 == 0}">
                    <tr>
                        <td>Column Name</td>
                        <td><input type="text" name="settings[]" value="${settings[inputs]}"/></td>
                            </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>Value</td>
                        <td><input type="text" name="settings[]" value="${settings[inputs]}"/></td>
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
            <td><input type="submit" value="Найти" name="put"/></td>
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
