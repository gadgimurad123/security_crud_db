<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <title>User info</title>
    <style>
        <%@include file='style/style.css' %>
    </style>
</head>

<body>

<div class="centre">

    <a href="<c:url value="/logout"/>">Logout</a>

    <h3>User Info</h3>
    <br>

    <form:form method="post" action="saveUser" modelAttribute="user">

        <form:hidden path="id"/>
        <table class="centre">
            <tr>
                <td class="centre">Name</td>
                <td class="centre"><form:input path="name"/></td>
            </tr>
            <tr>
                <td class="centre">Last name</td>
                <td class="centre"><form:input path="lastName"/></td>
            </tr>
            <tr>
                <td class="centre">Age</td>
                <td class="centre"><form:input path="age"/></td>
            </tr>
            <tr>
                <td class="centre">Email</td>
                <td class="centre"><form:input path="email"/></td>
            </tr>
            <tr>
                <td class="centre">Password</td>
                <td class="centre"><form:input path="password"/></td>
            </tr>
            <tr>
                <td class="centre">Role(s) select</td>
                <td class="centre">
                        <%--        <form:form method="post" modelAttribute="selectForm">--%>
                    <form:select path="roles" size="2" multiple="multiple">3
                        <form:options items="${userRolesList}"/>
                    </form:select>
                        <%--        </form:form>--%>
                </td>
            </tr>
        </table>
        <input type="submit" value="OK">

    </form:form>


</div>

</body>

</html>