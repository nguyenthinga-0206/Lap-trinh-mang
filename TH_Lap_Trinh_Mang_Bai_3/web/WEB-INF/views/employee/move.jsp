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
        <h2 class="form-group">Nhân viên của phòng ${department.getName()}</h2>
    </center>
    <p>
        <c:if test='${requestScope["message"]!=null}'>
            <span class="alert alert-info" role="alert">${requestScope["message"]}</span>
        </c:if>
    </p>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Họ và tên</th>
            <th>Ngày sinh</th>
            <th>Địa chỉ</th>
            <th>Email</th>
            <th>Số điện thoại</th>
            <th>Vị trí</th>
            <th>Phòng ban mới</th>
            <th>Move</th>
        </tr>
        </thead>
        <c:forEach items='${requestScope["employees"]}' var="employee">
            <form method="post">
                <tr>
                    <input type="hidden" name="idEmployee" value="${employee.getId()}">
                    <td>${employee.getName()}</td>
                    <td>${employee.getBirthday()}</td>
                    <td>${employee.getAddress()}</td>
                    <td>${employee.getEmail()}</td>
                    <td>${employee.getPhone()}</td>
                    <td>${employee.getPosition()}</td>
                    <td><select name="departments_id">
                        <c:forEach items="${departments}" var="temp">
                        <c:if test="${requestScope['employee'].getDepartment().getId()==temp.getId()}">
                        <option value="${temp.getId()}" selected> ${temp.getName()}</option>
                        </c:if>
                        <c:if test="${requestScope['employee'].getDepartment().getId()!=temp.getId()}">
                        <option value="${temp.getId()}"> ${temp.getName()}</option>
                        </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <button type="submit" class="btn btn-lg" style="background-color:transparent;">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <div class="row">
        <div class="col-sm-12">
            <div class="text-center">
                <button class="btn btn-primary" type="button"
                        onclick="location.href='/departments?action=delete&id=${department.getId()}';">Hoàn tất
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

