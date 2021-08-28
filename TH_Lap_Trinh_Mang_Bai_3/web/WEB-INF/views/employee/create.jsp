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
    <title>Thêm mới nhân viên</title>
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
        <h2 class="form-group">Thêm mới nhân viên</h2>
        <p>
            <c:if test='${requestScope["message"]!=null}'>
                <span class="alert alert-info" role="alert">${requestScope["message"]}</span>
            </c:if>
        </p>
        <form method="post">
            <fieldset class="scheduler-border">
                <legend class="scheduler-border">Thông tin của nhân viên</legend>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputName">Tên nhân viên :</label>
                            <input type="text" class="form-control" id="inputName" name="name"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputBirthday">Ngày sinh :</label>
                            <input type="date" class="form-control" id="inputBirthday" name="birthday"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label input-label" for="inputAddress">Địa chỉ :</label>
                    <input type="text" class="form-control" id="inputAddress" name="address"/>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputEmail">Email :</label>
                            <input type="text" class="form-control" id="inputEmail" name="email"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputPhone">Số điện thoại :</label>
                            <input type="text" class="form-control" id="inputPhone" name="phone"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputPosition">Vị trí:</label>
                            <input type="text" class="form-control" id="inputPosition" name="position"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label input-label" for="inputDepartment">Phòng ban:</label>
                        <select name="departments_id" id="inputDepartment" class="form-control">
                            <c:forEach items="${departments}" var="department">
                                <option value="${department.getId()}">${department.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Thêm mới">
                </div>
            </fieldset>
        </form>
    </div>

</div>
</body>
</html>

