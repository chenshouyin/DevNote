<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dologin.jsp' starting page</title>
    
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
    <jsp:useBean id="myUsers" class="com.po.Users" scope="page"/>
    <h1>setProperty动作元素</h1>
    <hr>
   <!--根据表单自动匹配所有的属性 -->
   <%-- 
   <jsp:setProperty name="myUsers" property="*"/>  
   --%>
   <!--根据表单匹配所有部分的属性 -->
   <%-- 
   <jsp:setProperty name="myUsers" property="username"/>  
   --%>
   <!--根表单无关，通过手工赋值给属性 -->
   <%-- 
   <jsp:setProperty name="myUsers" property="username" value="lisi"/>
   <jsp:setProperty name="myUsers" property="password" value="888888"/>
   --%>
   <!--通过URL传参数给属性赋值 -->
   <jsp:setProperty name="myUsers" property="username"/>
   <jsp:setProperty name="myUsers" property="password" param="mypass"/>
   <!-- 使用传统的表达式方式来获取用户名和密码 -->
   <%--     
       用户名：<%=myUsers.getUsername() %><br>
       密码：<%=myUsers.getPassword() %><br> 
   --%>
   <!-- 使用getProperty方式来获取用户名和密码 -->
      用户名：<jsp:getProperty name="myUsers" property="username"/> <br>
      密码：<jsp:getProperty name="myUsers" property="password"/><br>
   <br>
   <br>
      <a href="testScope.jsp">测试javabean的四个作用域范围</a>
      <% 
         request.getRequestDispatcher("testScope.jsp").forward(request, response);
      %>
  </body>
</html>
