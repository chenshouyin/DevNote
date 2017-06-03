<%@ page language="java" import="java.util.*,java.text.*" contentType="text/html; charset=utf-8"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 Date date = new Date();
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
 String s = sdf.format(date);
 out.println(s+"<br>");
%>
