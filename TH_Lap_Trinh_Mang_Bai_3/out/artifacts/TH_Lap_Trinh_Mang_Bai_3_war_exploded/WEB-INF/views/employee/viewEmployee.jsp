<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 15/10/2019
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông tin của nhân viên</title>
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
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>
<body>
<div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8">
        <h2>Thông tin của nhân viên</h2>
            <fieldset class="scheduler-border">
                <legend class="scheduler-border">Thông tin của nhân viên</legend>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputName">Tên nhân viên :</label>
                            <input type="text" class="form-control" id="inputName" name="name" readonly
                                   value='${requestScope["employee"].getName()}'/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputBirthday">Ngày sinh :</label>
                            <input type="date" class="form-control" id="inputBirthday" name="birthday" readonly
                                   value='${requestScope["employee"].getBirthday()}'/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label input-label" for="inputAddress">Địa chỉ :</label>
                    <input type="text" class="form-control" id="inputAddress" name="address" readonly
                           value='${requestScope["employee"].getAddress()}'/>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputEmail">Email :</label>
                            <input type="text" class="form-control" id="inputEmail" name="email" readonly
                                   value='${requestScope["employee"].getEmail()}'/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputPhone">Số điện thoại :</label>
                            <input type="text" class="form-control" id="inputPhone" name="phone" readonly
                                   value='${requestScope["employee"].getPhone()}'/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputPosition">Vị trí:</label>
                            <input type="text" class="form-control" id="inputPosition" name="position" readonly
                                   value='${requestScope["employee"].getPosition()}'/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputPosition">Phòng ban:</label>
                            <input type="text" class="form-control" name="position" readonly
                                   value='${requestScope["employee"].getDepartment().getName()}'/>
                        </div>
                    </div>
                </div>
            </fieldset>

    </div>
</div>
</body>
</html>