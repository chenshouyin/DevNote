<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
方式一:用标签取出</br>

<s:property value="#request.name"/>
</br>

<s:property value="#session.name"/>
</br>

<s:property value="#application.name"/>
</br>
</br>
方式二:用原始的jsp内置对象取出</br>
<%out.println(request.getAttribute("name")); %></br>
<%out.println(session.getAttribute("name")); %></br>
<%out.println(application.getAttribute("name")); %></br>

<s:debug/>
</body>
</html>