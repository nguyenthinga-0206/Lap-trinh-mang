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
    <title>Thêm mới phòng ban</title>
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
    <div class="col-md-5">
        <h2 class="form-group">Thêm mới phòng ban</h2>
        <p>
            <c:if test='${requestScope["message"]!=null}'>
                <span class="alert alert-info" role="alert">${requestScope["message"]}</span>
            </c:if>
        </p>
        <form method="post">
            <fieldset class="scheduler-border">
                <legend class="scheduler-border">Thông tin của phòng ban</legend>
                <div class="form-group">
                    <label class="control-label input-label" for="inputName">Tên phòng ban :</label>
                    <input type="text" class="form-control" id="inputName" name="name"/>
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

