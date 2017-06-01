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

<title>jsp变量定义的二种方式</title>

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
	变量定义的二种方式
	<br>
	<br>

	<!--HTML注释，通过浏览器查看网页源代码时可以看见注释内容-->
	<%--jsp注释,客户端不可见 --%>

	<%!String s1 = "变量定义方式1";%>
	第一种定义方式
	<%=s1%><br>

	<%
		String s2 = "变量定义方式2";
	%>

	第二种定义方式
	<%=s2%>

	<br>
	<br> 区别:
	<%!%>声明 全局变量，可声明变量,也可声明方法
	<%%>脚本段 可以声明局部变量，但不能声明方法 中属于局部变量，不同客户访问同一页面不能共享


	<br>
	<br>

	<%!int count1 = 0;

	synchronized int add1() {
		return count1++;
	}%>
	统计1:<%=add1()%>

	<%
		int count2 = 0;
	%>
	统计2:<%=count2++%>
	<br>可以看到访问该页面的时候,第一种方式会自增,第二种不会
</body>
</html>
