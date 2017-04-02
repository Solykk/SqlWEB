<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Tables</title>
    </head>
    <body>
        <form action="tables" method="get">
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
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>