
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
    <title>Приветствие пользователя!</title>
</head>
<body>

<c:out value="${login}"/>, благодарим за использоване нашего сайта!

            <td>Логин</td>
            <td>Пароль</td>
<td>Редактировать</td>
<td>Удалить</td>
        </tr>
        <tr>
            <td>${login}</td>
            <td>${pass}</td>
            <td><a href="/delete?id=${id}">Удалить</a></td>
            <br>
        </tr>
    </table>
</div>
</body>
</html>
</body>
</html>
