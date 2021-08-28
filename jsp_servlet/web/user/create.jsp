<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="static/bootstrap-4/css/bootstrap.css">
    <link rel="stylesheet" href="static/bootstrap-4/js/bootstrap.js">
</head>
<body>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<div align="center">
    <h1>Nhap user muon them</h1>
    <form method="post">
        <table border="1" cellpadding="5" width="500px">
            <center>
                <h5>
                    <a href="users?action=users">Danh sach tat ca user</a>
                </h5>
            </center>
            <tr>
                <th>Phan quyen:</th>
                <td>
                    <input type="number" min="1" max="2" name="roles" id="roles" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Tai khoan:</th>
                <td>
                    <input type="text" name="username" id="username"/>
                </td>
            </tr>
            <tr>
                <th>Mat khau:</th>
                <td>
                    <input type="password" name="password" id="password"/>
                </td>
            </tr>
            <tr>
                <th>Ten dem:</th>
                <td>
                    <input type="text" name="firsname" id="firsname"/>
                </td>
            </tr>
            <tr>
                <th>Ten:</th>
                <td>
                    <input type="text" name="lastname" id="lastname"/>
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