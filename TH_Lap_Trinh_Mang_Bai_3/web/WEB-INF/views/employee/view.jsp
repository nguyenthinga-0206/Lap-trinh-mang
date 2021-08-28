<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách nhân viên</title>
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>
<body>
<div class="container">
    <center>
        <h2>Danh sách nhân viên của phòng ${department.getName()}</h2>
    </center>
    <table class="table">
        <thead class="thead-light">
        <tr class="row">
            <th class="col-md-3 ">Họ và tên nhân viên</th>
            <th class="col-md-3 ">Số điện thoại</th>
            <th class="col-md-3 ">Vị trí</th>
            <th class="col-md-3">Chức năng</th>

        </tr>
        </thead>
        <c:forEach items='${requestScope["employees"]}' var="employee">
            <tr class="row">
                <td class="col-md-3 "><a href="/employees?action=viewEmployee&id=${employee.getId()}">${employee.getName()}</a></td>
                <td class="col-md-3 ">${employee.getPhone()}</td>
                <td class="col-md-3 ">${employee.getPosition()}</td>
                <td class="col-md-3">
                    <button class="btn btn-lg" style="background-color:transparent;"
                            onclick="location.href='/employees?action=edit&id=${employee.getId()}';">
                        <i class="fa fa-edit"></i>
                    </button>
                    <button class="btn btn-lg" style="background-color:transparent;"
                            onclick="location.href='/employees?action=delete&id=${employee.getId()}';">
                        <i class="fa fa-trash"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

