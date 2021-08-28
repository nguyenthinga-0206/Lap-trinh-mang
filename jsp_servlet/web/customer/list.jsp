<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 8/13/2021
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Product</title>
    <link rel="stylesheet" href="../static/css/bootstrap.css">
</head>
<body>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<br/>
<h1 class="text-center">DANH SÁCH SẢN PHẨM</h1>
<h4 class="text-center text-primary"><a href="/customers?action=create">Thêm sản phẩm</a></h4>
<table class="table text-center">
    <tr class="row bg-success">
        <th class="col-2">Ten San Pham</th>
        <th class="col-2">Gia Tien</th>
        <th class="col-2">So Luong</th>
        <th class="col-2">Quoc Gia</th>
        <th class="col-2">Edit</th>
        <th class="col-2">Delete</th>
    </tr>
<%--    <c:forEach var="product" items="${products}">--%>
<%--        <tr class="row" style="background: #c3e6cb">--%>
<%--            <td class="col-2"><a href="/customers?action=view&id=${product.id}" style="color: black">${product.name}</a>--%>
<%--            </td>--%>
<%--            <td class="col-2">${product.price}</td>--%>
<%--            <td class="col-2">${product.amount}</td>--%>
<%--            <td class="col-2">${product.country}</td>--%>
<%--            <td class="col-2 font-weight-bold">--%>
<%--                <button type="button" class="btn btn-info">--%>
<%--                    <a href="/customers?action=edit&id=${product.id}" class="text-light">Edit</a>--%>
<%--                </button>--%>
<%--            </td>--%>
<%--            <td class="col-2 font-weight-bold">--%>
<%--                <button type="button" class="btn btn-danger">--%>
<%--                    <a href="/customers?action=delete&id=${product.id}" class="text-light">Delete</a>--%>
<%--                </button>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
</table>

</body>
</html>
