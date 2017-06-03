<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <h1>out内置对象</h1>
    <% 
       out.println("<h2>静夜思</h2>");
       out.println("床前明月光<br>");
       out.println("疑是地上霜<br>");
       out.flush();
       //out.clear();//这里会抛出异常。
       out.clearBuffer();//这里不会抛出异常。
       out.println("举头望明月<br>");
       out.println("低头思故乡<br>");
    
    %>
        缓冲区大小：<%=out.getBufferSize() %>byte<br>
        缓冲区剩余大小：<%=out.getRemaining() %>byte<br>
       是否自动清空缓冲区：<%=out.isAutoFlush() %><BR>    
  </body>
</html>
