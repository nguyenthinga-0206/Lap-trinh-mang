<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 4/27/2021
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<center>
    <h2>Danh sách Phòng ban</h2>
</center>
<p>
    <c:if test='${requestScope["message"]!=null}'>
        <span class="alert alert-info" role="alert">${requestScope["message"]}</span>
    </c:if>
</p>
<div class="row form-group">
    <button class="btn btn-lg " style="background-color:transparent;"
            onclick="location.href='/users?action=create';">
        <i class="fa fa-plus-square"></i> Thêm user
    </button>
</div>
<table class="table">
    <thead class="thead-light">
    <tr class="row">
        <th scope="col" class="col-md-4">Roles</th>
        <th scope="col" class="col-md-2">Username</th>
        <th scope="col" class="col-md-2">Password</th>
        <th scope="col" class="col-md-2">Firsname</th>
        <th scope="col" class="col-md-2">Lastname</th>

    </tr>
    </thead>
    <c:forEach items='${requestScope["users"]}' var="users">
        <tr class="row">
            <td class="col-md-4"><a
                    href="/users?action=user&id=${users.getId()}">${users.getName()}</a></td>
            <td class="col-md-2">
                <button class="btn btn-lg" style="background-color:transparent;"
                        onclick="location.href='/users?action=update&id=${users.getId()}';">
                    <i class="fa fa-edit"></i>
                </button>
            </td>
            <td class="col-md-2">
                <button class="btn btn-lg" style="background-color:transparent;"
                        onclick="location.href='/users?action=delete&id=${users.getId()}';">
                    <i class="fa fa-trash"></i>
                </button>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
