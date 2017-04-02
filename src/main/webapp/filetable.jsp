<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FileTable</title>
</head>
<body>
<form action="FileTable" method="post">
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
</body>
</html>
