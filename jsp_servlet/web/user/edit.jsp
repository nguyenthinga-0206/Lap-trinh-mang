<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="static/bootstrap-4/css/bootstrap.css">
    <link rel="stylesheet" href="static/bootstrap-4/js/bootstrap.js">
    <style>
        table {
            margin: auto;
        }
        .row {
            padding: 10px 10px;
        }
        .input {
            width: 300px;
        }
        th {
            color: #0c5460;
        }
        button {
            background: #9fcdff;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<div align="center">
    <h1>
        User dang sua
    </h1>
    <form method="post">
        <table border="1" cellpadding="5" width="500px">
            <center>
                <h3>
                    <a href="users?action=users">Danh sach tat ca user</a>
                </h3>
            </center>
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
            </c:if>
            <tr>
                <th>Phan quyen:</th>
                <td>
                    <input type="number" min="1" max="2" name="roles"
                           value="<c:out value='${user.roles}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Tai khoan:</th>
                <td>
                    <input type="text" name="username"
                           value="<c:out value='${user.username}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Mat khau:</th>
                <td>
                    <input type="password" name="password"
                           value="<c:out value='${user.password}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Ten dem:</th>
                <td>
                    <input type="text" name="firsname"
                           value="<c:out value='${user.firsname}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Ten:</th>
                <td>
                    <input type="text" name="lastname"
                           value="<c:out value='${user.lastname}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save" style="font-size: 20px; width: 70px;height: 50px; float: right"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>