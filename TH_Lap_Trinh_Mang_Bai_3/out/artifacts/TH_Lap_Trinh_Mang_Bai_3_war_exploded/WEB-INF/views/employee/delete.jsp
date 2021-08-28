<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 15/10/2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xóa nhân viên</title>
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
    <style>
        fieldset.scheduler-border {
            border: 1px groove #ddd !important;
            padding: 0 1.4em 1.4em 1.4em !important;
            margin: 0 0 1.5em 0 !important;
            -webkit-box-shadow: 0px 0px 0px 0px #000;
            box-shadow: 0px 0px 0px 0px #000;
            background-color: #dddddd;
        }

        legend.scheduler-border {
            width: inherit; /* Or auto */
            padding: 0 10px; /* To give a bit of padding on the left and right */
            border-bottom: none;
        }
    </style>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>
<body>
<div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-5">
        <h2>Xóa nhân viên</h2>
        <form method="post">
            <div class="alert alert-danger" role="alert">
                <h6>Bạn có chắc là muốn xóa?</h6>
            </div>
            <fieldset class="scheduler-border">
                <legend class="scheduler-border">Thông tin của nhân viên</legend>
                <div class="form-group">
                    <label class="control-label input-label">Tên nhân viên :</label>
                    ${requestScope["employee"].getName()}
                </div>
                <div class="form-group">
                    <label class="control-label input-label">Ngày sinh :</label>
                    ${requestScope["employee"].getBirthday()}
                </div>
                <div class="form-group">
                    <label class="control-label input-label">Địa chỉ :</label>
                    ${requestScope["employee"].getAddress()}
                </div>
                <div class="form-group">
                    <label class="control-label input-label">Email :</label>
                    ${requestScope["employee"].getEmail()}
                </div>
                <div class="form-group">
                    <label class="control-label input-label">Số điện thoại :</label>
                    ${requestScope["employee"].getPhone()}
                </div>
                <div class="form-group">
                    <label class="control-label input-label">Vị trí :</label>
                    ${requestScope["employee"].getPosition()}
                </div>
                <div class="form-group">
                    <label class="control-label input-label">Phòng ban :</label>
                    ${requestScope["employee"].getDepartment().getName()}
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Xóa">
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
