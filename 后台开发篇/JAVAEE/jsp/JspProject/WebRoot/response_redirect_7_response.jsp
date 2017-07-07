<%@page import="java.io.PrintWriter"%>
<%@page import="javax.transaction.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
//response.getWriter()和内置out对象都可以输出，区别是前者输出虽然书写顺序不一定，但是输 Cannot call sendRedirect() after the response has been committed
//出一定在 后者前面 除非使用flush强制输出"
	response.setContentType("text/html;charset=utf-8");
	
	out.println("书写顺序1 我是内置out对象</br>");
	out.println("</hr>");
	//out.flush(); 向浏览器发送数据  再重定向是不行的   
	
	PrintWriter printWriter = response.getWriter();
	printWriter.println("书写顺序2我是response内置对象PrintWriter输出流");
	
	//response.sendRedirect("register_request_recieve_2_5.jsp");//重定向表单参数不会保存 浏览器地址会变
	request.getRequestDispatcher("register_request_recieve_2_5.jsp").forward(request, response);//请求转发 表单数据会传递 浏览器地址不会变
%>

