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

<title>参数接收request 传递通过post或者url已经解决中文乱码</title>

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
	

	<form action="register_request_recieve_2_5.jsp" method="post">
	<table>
	<tr>
	<td>用户名:</td><td><input type="text" name="userName"></td>
	</tr>
		<td>密码:</td><td><input type="password" name="inputPassWord"></td>
	
	</tr>
	
	
	
	<tr>
	<td>用户爱好</td>
		<td><input type="checkbox" name="cheakboxUserFvorite" value="music"/>音乐
		<input type="checkbox" name="cheakboxUserFvorite" value="read">读书
		<input type="checkbox" name="cheakboxUserFvorite" value="tv">看电视
		<input type="checkbox" name="cheakboxUserFvorite" value="pricatic">锻炼</td>
		
	</tr>
	
	<tr><td><input type="submit" name="submitLogin"></td></tr>
	</table>
	
	</form>
	
	
	</br>
	<a href="register_request_recieve_2_5.jsp？userName=小c测试中文">通过get方式传参数</a>
</body>
</html>
