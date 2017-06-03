<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.text.*" %>
<%
   Date d = new Date();
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
   String s = sdf.format(d);
   out.println(s);
%>
