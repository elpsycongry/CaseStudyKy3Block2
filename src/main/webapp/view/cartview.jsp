<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/10/2023
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>My cart</h1>
<table border="1px" style="width: 60%; margin: auto">
    <tr>
        <th>stt</th>
        <th>date</th>
        <th>description</th>
        <th>urlImage</th>
        <th>namePlace</th>
        <th>Buy</th>
    </tr>
    <c:forEach var="item" items="${sessionScope.cartList}" varStatus="index">
        <tr>
            <td>${index.begin}</td>
            <td>${item.date}</td>
            <td>${item.description}</td>
            <td> <img src="${item.urlImage}"/></td>
            <td>${item.namePlace}</td>
            <td><a href="/cart-controller?action=buy?id=${item.id}}">buy</a></td>
        </tr>
    </c:forEach>
    <a href="/main">Return</a>
</table>
</body>
</html>
