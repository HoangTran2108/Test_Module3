<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 20/3/2023
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
    <h1>Employee Management</h1>
    <h2>
        <a href="EmployeeServlet">List All Employee</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Employee</h2>
            </caption>
            <tr>
                <th>Name:</th>
                <td>
                    <input type="text" name="eName" id="eName" size="45"/>
                </td>
            </tr>
            <tr>
                <th>email:</th>
                <td>
                    <input type="text" name="email" id="email" size="45"/>
                </td>
            </tr>
            <tr>
                <th>address:</th>
                <td>
                    <input type="text" name="address" id="address" size="45"/>
                </td>
            </tr>
            <tr>
                <th>phoneNumber:</th>
                <td>
                    <input type="text" name="phoneNumber" id="phoneNumber" size="45"/>
                </td>
            </tr>
            <tr>
                <th>salary:</th>
                <td>
                    <input type="number" name="salary" id="salary" size="45"/>
                </td>
            </tr>
            <tr>
                <th>dId:</th>
                <td>
                    <input type="number" name="dId" id="dId" size="45"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Add"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
