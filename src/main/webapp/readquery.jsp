<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>ReadQuery</title>
</head>
<body>
    <form action="readquery" method="post">
        <p><b>Введита Ваш SQL запрос</b></p>
        <p><textarea rows="10" cols="45" name="cudQuery"></textarea></p>
        <p><input type="submit" value="Отправить"></p>
    </form>
    <%@include file="footer.jsp" %>
</body>
</html>
