<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
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
    <h1>用户资料</h1>
    <hr>
    <% 
       request.setCharacterEncoding("utf-8");
       String username = "";
       String password = "";
       String email = "";
       if(request.getParameter("username")!=null)
       {
          username = request.getParameter("username");
       }
       if(request.getParameter("password")!=null)
       {
          password = request.getParameter("password");
       }
       if(request.getParameter("email")!=null)
       {
          email = request.getParameter("email");
       }
       
    %>
        用户名：<%=username %><br>
        密码：<%=password %><br>
        电子邮箱：<%=email %><br>
  </body>
</html>
