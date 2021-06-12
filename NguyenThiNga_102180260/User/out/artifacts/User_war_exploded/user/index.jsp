<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>login</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      font-size: 20px;
      padding: 1px 35% ;
    }

    h1{
      text-align: center;
      color: red;
      padding: 5px 5px;

    }
    table{

      width: 100%;
      height: 60%;
      color: black;
    }
    tr{
      height: 30px;
    }

    td{
      float: left;
      width: 27%;
      height: 100%;
      padding-top: 10px;
      padding-bottom: 5px;
    }
    th{
      float: right;
      width: 70%;
      height: 100%;
    }
    input{
      width: 100%;
      height: 70%;
      font-size: 20px;
    }

  </style>
</head>
<body>
<form action="/users" method="post">
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
      <td style="float: right; width: 20%">
        <input type="submit" value="Login"/>
      </td>
    </tr>
  </table>

</form>

</body>
</html>
