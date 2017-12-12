<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>Jsp中使用JavaBean</p>
参照:<a href="http://www.runoob.com/jsp/jsp-actions.html">JSP 动作元素</a>
<br>jsp:setProperty用来设置已经实例化的Bean对象的属性，有两种用法。首先，你可以在jsp:useBean元素的外面（后面）使用jsp:setProperty，如下所示： 
<jsp:useBean id="userInfo" class="com.csy.bean.UserBean"></jsp:useBean>
</body>
</html>