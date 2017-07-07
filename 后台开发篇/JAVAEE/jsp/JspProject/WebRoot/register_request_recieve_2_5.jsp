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
		out.print(userName+"</br>");
	%>
	
	
	<%		
		String passWord = request.getParameter("passWord");
		out.print("</br>密码:"+passWord);
		request.setAttribute("passWord",passWord);
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
	
	</br>request.setAttribute存储参数  request.getAttribute  密码:
	<%
		out.print(request.getAttribute("passWord"));
	 %>
	 
	 <%--request的其它属性 --%>

<%
out.print("</br>请求体的MIME:"+request.getContentType());
out.print("</br>协议类型和版本号:"+request.getProtocol());
out.print("</br>服务器主机名:"+request.getServerName());
out.print("</br>服务器主机端口:"+request.getServerPort());
out.print("</br>请求文件的长度"+request.getContentLength());
out.print("</br>请求客户端的Ip地址:"+request.getRemoteAddr());
out.print("</br>请求的真实路径:"+request.getRealPath("register_request_recieve_2_5.jsp"));
out.print("</br>请求的上下文路径:"+request.getContextPath());

	 %>
</body>
</html>
