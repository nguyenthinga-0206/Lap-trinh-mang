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
    <h1>Danh sach user</h1>
    <table class="table text-center">
        <center>
            <h5 class="text-center text-primary">
                <a href="/users?action=create">Them user moi</a>
            </h5>
        </center>
        <tr class="row bg-success">
            <th class="col-2">Tai khoan</th>
            <th class="col-2">Ten dem</th>
            <th class="col-2">Ten</th>
            <th class="col-2">Phan quyen</th>
            <th class="col-2">Edit</th>
            <th class="col-2">Delete</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr class="row" style="background: #c3e6cb">
                <td class="col-2"><c:out value="${user.username}"/></td>
                <td class="col-2"><c:out value="${user.firsname}"/></td>
                <td class="col-2"><c:out value="${user.lastname}"/></td>
                <td class="col-2">
                    <c:choose>
                        <c:when test="${user.roles == 1}">Admin</c:when>
                        <c:when test="${user.roles == 2}">User</c:when>
                    </c:choose>
                </td>
                <td class="col-2">
                    <a href="/users?action=edit&id=${user.id}">Sua</a>
                </td>
                <td class="col-2">
                    <a href="/users?action=delete&id=${user.id}">Xoa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <h5>${requestScope["message"]}</h5>
</div>
</body>
</html>