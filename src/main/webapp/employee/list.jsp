<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 20/3/2023
  Time: 10:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management Application</title>
</head>
<body>
<center>
    <h1>Employee Management</h1>
    <a href="/EmployeeServlet?action=view">Search by name</a>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>address</th>
            <th>phoneNumber</th>
            <th>salary</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="employee" items="${requestScope.listEmployee}">
            <tr>
                <td><c:out value="${employee.id}"/></td>
                <td><c:out value="${employee.name}"/></td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.address}"/></td>
                <td><c:out value="${employee.phoneNumber}"/></td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.department.getdId()}"/></td>
                <td>
                    <a href="/EmployeeServlet?action=edit&id=${employee.id}">Edit</a>
                    <a href="/EmployeeServlet?action=delete&id=${employee.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p></p>
    <h2>
        <a href="/EmployeeServlet?action=create">Add New Employee</a>
    </h2>
</div>
</body>
</html>