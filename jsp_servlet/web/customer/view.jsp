<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 8/13/2021
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Product</title>
    <link rel="stylesheet" href="../static/css/bootstrap.css">
    <style>
        table {
            margin-left: 43%;
        }

        th {
            color: #0c5460;
            font-weight: bold;
        }

        button {
            background: #9fcdff;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<br/>
<h1 class="text-center">THÔNG TIN SẢN PHẢM</h1>
<table>
    <tr class="row">
        <th class="col-6">Ten San Pham</th>
        <td class="col-6">
            ${requestScope["product"].getName()}
        </td>
    </tr>
    <tr class="row">
        <th class="col-6">Gia Tien</th>
        <td class="col-6">
            ${requestScope["product"].getPrice()}
        </td>
    </tr>
    <tr class="row">
        <th class="col-6">So Luong</th>
        <td class="col-6">
            ${requestScope["product"].getAmount()}
        </td>
    </tr>
    <tr class="row">
        <th class="col-6">Quoc Gia</th>
        <td class="col-6">
            ${requestScope["product"].getCountry()}
        </td>
    </tr>
</table>
</body>
</html>
