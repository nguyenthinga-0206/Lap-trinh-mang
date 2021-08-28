<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách phòng ban</title>
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>

<body>
<div class="container">
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
                onclick="location.href='/departments?action=create';">
            <i class="fa fa-plus-square"></i> Thêm mới phòng ban
        </button>
    </div>
    <table class="table">
        <thead class="thead-light">
        <tr class="row">
            <th scope="col" class="col-md-4">Tên phòng ban</th>
            <th scope="col" class="col-md-2">Chỉnh sửa</th>
            <th scope="col" class="col-md-2">Xóa</th>
            <th scope="col" class="col-md-2">Upload</th>
            <th scope="col" class="col-md-2">Download</th>

        </tr>
        </thead>
        <c:forEach items='${requestScope["departments"]}' var="department">
                <tr class="row">
                    <td class="col-md-4"><a
                            href="/departments?action=view&id=${department.getId()}">${department.getName()}</a></td>
                    <td class="col-md-2">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/departments?action=edit&id=${department.getId()}';">
                            <i class="fa fa-edit"></i>
                        </button>
                    </td>
                    <td class="col-md-2">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/departments?action=delete&id=${department.getId()}';">
                            <i class="fa fa-trash"></i>
                        </button>
                    </td>
                    <td class="col-md-2">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/departments?action=upload&id=${department.getId()}';">
                            <i class="fa fa-upload"></i>
                        </button>
                    </td>
                    <td class="col-md-2">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/departments?action=export&id=${department.getId()}';">
                            <i class="fa fa-download"></i>
                        </button>
                    </td>
                </tr>

        </c:forEach>
    </table>
</div>

</body>
</html>

