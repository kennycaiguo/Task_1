<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 04.03.2017
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<center>
    <h2>
        <a href="view">All Data</a>
    </h2>
</center>
<div align="center">
    <c:if test="${users_hib != null}">
    <form action="update">
        </c:if>
        <c:if test="${users_hib == null}">
        <form action="add" method="post">
            </c:if>
            <table border="1">
                <caption>
                    <h2>
                        <c:if test="${users_hib != null}">
                            Edit User
                        </c:if>
                        <c:if test="${users_hib == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${users_hib != null}">
                    <input type="hidden" name="id" value="<c:out value='${users_hib.id}' />" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${users_hib.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td>
                        <input type="text" name="email" size="45"
                               value="<c:out value='${users_hib.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>password: </th>
                    <td>
                        <input type="text" name="password" size="45"
                               value="<c:out value='${users_hib.password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
