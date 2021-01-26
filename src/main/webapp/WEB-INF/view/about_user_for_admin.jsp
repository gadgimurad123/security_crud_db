<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>About user</title>
    <style>
        <%@include file='style/style.css' %>
    </style>
</head>

<body>

<div class="centre">

    <a href="<c:url value="/logout"/>">Logout</a>

    <h3>Admin page: about user</h3>

    <table class="centre">
        <tr>
            <td class="centre">Name:</td>
            <td class="centre">${user.name}</td>
        </tr>
        <tr>
            <td class="centre">Last name:</td>
            <td class="centre">${user.lastName}</td>
        </tr>
        <tr>
            <td class="centre">Age:</td>
            <td class="centre">${user.age}</td>
        </tr>
        <tr>
            <td class="centre">Email:</td>
            <td class="centre">${user.email}</td>
        </tr>
        <tr>
            <td class="centre">Password:</td>
            <td class="centre">${user.password}</td>
        </tr>
        <tr>
            <td class="centre">Roles:</td>
            <td class="centre">
                <c:forEach var="role" items="${user.roles}">
                    <c:out value="${role.getRole()}"/>
                </c:forEach>
            </td>
        </tr>
    </table>

</div>

</body>

</html>
