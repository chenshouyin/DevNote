<%@page import="java.io.PrintWriter"%>
<%@page import="javax.transaction.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
//response.getWriter()和内置out对象都可以输出，区别是前者输出虽然书写顺序不一定，但是输
//出一定在 后者前面 除非使用flush强制输出"
	response.setContentType("text/html;charset=utf-8");
	
	out.println("书写顺序1 我是内置out对象</br>");
	out.println("</hr>");
	
	PrintWriter printWriter = response.getWriter();
	printWriter.println("书写顺序2我是response内置对象PrintWriter输出流");
%>

