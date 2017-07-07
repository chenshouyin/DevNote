<%@page import="javax.transaction.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登陆get方式提交</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	

	<form action="login_get_success4.jsp" method="post">
	<table>
	<tr>
	<td>用户名:</td><td><input type="text" name="inputName"></td>
	</tr>
		<td>密码:<input type="password" name="passWord"></td>
	
	</tr>
	
	<tr>提交:<input type="submit" name="submitLogin"></tr>
	</table>
	
	</form>
</body>
</html>
