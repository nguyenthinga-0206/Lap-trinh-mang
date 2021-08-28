<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 5/8/2021
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        h1 {
            color: crimson;
        }

        body {
            padding: 1px 400px 10px 400px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td, th {
            /*padding: 20px;*/
            /*border: 2px solid black;*/
        }

        th {
            text-align: center;
            font-weight: 400;
            color: blue;
            padding: 20px;
            font-size: 26px;
            /*border: 2px solid black;*/
        }

        td {
            text-align: center;
            font-size: 24px;
            color: black;
        }

        input {
            width: 100%;
            height: 60px;
        }

    </style>
</head>
<body align="center">
<form action="/loginServlet" method="post">
    <h1>LOGIN</h1>
    <table>
        <tr>
            <td>
                UserName:
            </td>
            <th>
                <input type="text" name="username" placeholder="UserName"/>
            </th>
        </tr>
        <tr>
            <td>
                PassWord:
            </td>
            <th>
                <input type="text" name="password" placeholder="PassWord"/>
            </th>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Login" style="font-size: 24px; width: 80px;height: 60px;float: right; margin-right: 40px;"/>
            </td>
        </tr>
    </table>
    <br>${message}
</form>
</body>
</html>
