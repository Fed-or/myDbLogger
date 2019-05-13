
<%--
  Created by IntelliJ IDEA.
  User: Adi
  Date: 10.05.2019
  Time: 5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello User!</title>
</head>
<body>

<c:out value="${login}"/>, you are admin!!

    <td>ID</td>
    <td>Логин</td>
    <td>Пароль</td>
    <td>Роль</td>
    <td>Редактировать</td>
    <td>Удалить</td>
</tr>
<c:forEach var="user" items="${users}">
            <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.password}</td>
        <td>${user.role}</td>

        <td><a href="/edit?id=${user.idUsers}">Редактировать</a></td>
        <td><a href="/delete?id=${user.idUsers}">Удалить</a></td>
        <br>
    </tr>
</c:forEach>
</table
</body>
</html>
