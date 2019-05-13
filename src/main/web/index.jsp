<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title>Логин</title>
<body>
<center>
    Enter form
    <br><br>

    <c:if test="${isRegistered == true}">
        <p>You are successfull registrated!</p>
    </c:if>

    <c:out value="${error}"/>

    <c:if test="${param.message == 'isNotPresent'}">
        <p><h>"Such user is not present in database"</h></p>
    </c:if>

    <br><br>

    <form action="signin" method="post">
        <td>Логин <input type="text" name="login"/></td>
        <br><br>
        <tr>
            <td>Пароль <input type="text" name="pass"/></td>
        </tr>
        <br><br>
        <input type="submit">
        </tr>
        <br><br>
    </form>
    <br>
    Еще не зарегистрированы?
    <br>
    <a href="/registration">Регистрация</a>
</center>
</body>
</html>