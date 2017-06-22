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

<title>request接收参数</title>

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
	姓名:

	<%--解决post提交中文乱码 --%>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<%
		String userName = request.getParameter("userName");
		out.println(userName);
	%>
	<%--获取chekbox的值 --%>
	</br>爱好:
	<%
		String[] values = request.getParameterValues("cheakboxUserFvorite");
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				out.print(values[i] + "&nbsp;&nbsp;");
			}
		}
	%>

</body>
</html>
