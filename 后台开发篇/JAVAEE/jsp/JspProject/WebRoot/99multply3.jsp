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

<title>表格打印九九乘法表</title>

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
	<h1 align="center">表格打印九九乘法表</h1>
	<br>
	<table border="1" align="center">
		<% for(int i = 1;i<10;i++){//行
		%>
	
		<%--html标签不应该在代码块内 --%>
		<tr bordercolor="#ff00fa" >
			
			<%
		for(int j=1;j<=i;j++){//列
		   %>
		   <td><%=i %>*<%=j %> = <%= (i*j) %>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<% }%>

		</tr>

		<% }%>


	</table>


</body>
</html>
