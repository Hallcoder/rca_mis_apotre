<%--
  Created by IntelliJ IDEA.
  User: mia
  Date: 14.06.23
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create Parent</title>
</head>
<body>
<h2>Create Parent</h2>
<form action="createparent" method="post">
  <input type="text" name="firstName" required><br><br>
  <input type="text" name="lastName" required><br><br>
  <input type="email" name="email" required><br><br>
  <input type="text" name="phoneNumber" required><br><br>
  <input type="text" name="childName" required><br><br>

  <input type="submit" value="Create Parent">
</form>
</body>
</html>
