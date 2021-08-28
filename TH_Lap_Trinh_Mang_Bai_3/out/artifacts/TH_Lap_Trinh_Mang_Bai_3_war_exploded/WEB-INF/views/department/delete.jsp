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
    <title>Xóa phòng ban</title>
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
<div class="container">
    <h2>Xóa phòng ban</h2>
    <div class="alert alert-danger" role="alert">
        <h6>Nếu bạn xóa phòng ban thì sẽ xóa tất cả nhân viên trong phòng ban đó hoặc bạn có thể chuyển các nhân
            viên đó sang phòng ban khác!</h6>
    </div>
    <div class="alert alert-info" role="alert">
        <h7>     + Nhấn nút ok để xóa tất cả nhân viên của phòng ban đó.</h7>
        <br>
    </div>
    <div class="alert alert-info" role="alert">
        <h7>     + Nhấn nút move để chuyển các nhân viên sang phòng ban khác.</h7>
    </div>
    <form method="post">
        <fieldset class="scheduler-border">
            <legend class="scheduler-border">Thông tin của phòng ban</legend>
            <div class="form-group">
                <label class="control-label input-label">Tên phòng ban :</label>
                ${requestScope["department"].getName()}
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="ok">
                <a href="/departments?action=move&id=${requestScope["department"].getId()}"
                   class="btn btn-primary" >Move</a>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
