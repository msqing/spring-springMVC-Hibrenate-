<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sf"  uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
</head>
<body>
<div>${name}</div>
<form action="addUser.do" method="post">
姓名：<input type="text" name="name"/><br>
年龄：<input type="text" name="age"/><br>
<input type="submit" value="添加" />
</form>
</body>
</html>