<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>jsp语法学习</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset = utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	jsp相关知识学习
	<hr>

	<a href="define1.jsp">1.变量定义的二种方式</a>
	<br>
	<a href="getIp2.jsp">2.获取访问者ip地址</a>
	<br>
	<a href="99multply3.jsp">3.表格打印九九乘法表</a>	<br>
<a href="login_get_4.jsp">4.表单提交get</a><br>
<a href="login_post_4.jsp">4.表单提交post</a><br>
<a href="register_request_5.jsp">5.参数接收request 传递通过post或者url已经解决中文乱码</a>

</body>
</html>
