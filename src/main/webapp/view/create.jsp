<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="Create" method="post">
    <table>
        <tr>
            <td>Table Name</td>
            <td><input type="text" name="TableName"/></td>
        </tr>
    </table>

    <table>
        <c:forEach items="${inputVal}" var="inputs">

                    <tr>
                        <td>Column Name</td>
                        <td><input type="text" name="settings[]" /></td>
                        <td><input type="checkbox" name="pk" value="${inputs}" /></td>
                    </tr>

                    <tr>
                        <td>Data Type</td>
                        <td><input type="text" name="settings[]" /></td>
                    </tr>
                    <tr>
                        <td>Nullable</td>
                        <td><input type="checkbox" name="nullable[]" value="${inputs}"/></td>
                    </tr>

        </c:forEach>
        <tr>
            <td></td>
            <td><input type="submit" value="Добавить поле" name="add"/></td>
        </tr>
    </table>
    <table>
        <tr>
            <td>Sequence Generator</td>
            <td><input type="text" name="StartWith" /></td>
            <td><input type="checkbox" name="SeqTrue" /></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Создать" name="put"/></td>
        </tr>
    </table>
</form>
<%@include file="footer.jsp" %>
</body>
</html>
