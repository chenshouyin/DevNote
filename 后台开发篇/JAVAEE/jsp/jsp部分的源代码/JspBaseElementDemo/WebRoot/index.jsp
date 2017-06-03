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
    <h1>大家好</h1>
    <hr>
    <!-- 我是HTML注释，在客户端可见 -->
    <%-- 我是JSP注释，在客户端不可见 --%>
    <%! 
       String s = "张三";  //声明了一个字符串变量
       int add(int x,int y) //声明了一个返回整型的函数，实现两个整数的求和。
       {
          return x+y;
       }
    %>
    
    <% 
       //单行注释
       /*多行注释*/
       out.println("大家好，欢迎大家学习JAVAEE开发。");
    %>
    <br>
        你好，<%=s %><br>
    x+y=<%=add(10,5) %><br>    
  </body>
</html>
