<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>BookHub - Book</title>
</head>
<body>
<h3>Welcome, Enter The Book Details</h3>
<form:form method="post" action="" modelAttribute="book">
    <table>
        <tr>
            <td><form:label path="name">书名: <form:input path="name"/></form:label></td>
        </tr>
        <tr>
            <td><input type="submit" value="添加新书"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>