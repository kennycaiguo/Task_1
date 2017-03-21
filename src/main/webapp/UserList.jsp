<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  model.User: Dmitry
  Date: 28.02.2017
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<center>
    <h1>List All Users</h1>

</center>
<form method="GET" ></form>
<div align="center" >
    <table border="1">

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="users_hib" items="${userList}">
            <tr>
                <td>${users_hib.id}</td>
                <td>${users_hib.name}</td>
                <td>${users_hib.email}</td>
                <td>${users_hib.password}</td>

                <td>
                    <a href="getId?id=<c:out value='${users_hib.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${users_hib.id}' />" >Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2><a href="deleteAll">Delete All data</a></h2>
    <h2><a href="User.jsp">Add New User</a></h2>
    <h2><a href="index.jsp">Main page</a></h2>
</div>
</body>
</html>
