<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 8/13/2021
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
    <link rel="stylesheet" href="../static/css/bootstrap.css">
    <style>
        table {
            margin: auto;
        }

        .row {
            padding: 10px 10px;
        }

        .input {
            width: 300px;
        }

        th {
            color: #0c5460;
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
<h1 class="text-center">NHẬP THÔNG TIN SẢN PHẢM MỚI</h1>
<form method="post">
    <table>
        <tr class="row">
            <th class="col-4">Ten San Pham</th>
            <td class="col-8">
                <input class="input" name="name" placeholder="Nhập tên sản phẩm">
            </td>
        </tr>
        <tr class="row">
            <th class="col-4">Gia Tien</th>
            <td class="col-8">
                <input class="input" name="price" placeholder="Nhập giá sản phẩm">
            </td>
        </tr>
        <tr class="row">
            <th class="col-4">So Luong</th>
            <td class="col-8">
                <input class="input" name="amount" placeholder="Nhập số lượng">
            </td>
        </tr>
        <tr class="row">
            <th class="col-4">Quoc Gia</th>
            <td class="col-8">
                <input class="input" name="country" placeholder="Nhập quốc gia xuất xứ">
            </td>
        </tr>
        <tr class="row">
            <th class="col-4"></th>
            <th class="col-8">
                <input type="submit" class="p-1 font-weight-bold bg-success" name="add" value="Add Product">
            </th>
        </tr>
    </table>
</form>
</body>
</html>
