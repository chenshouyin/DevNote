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


Dispatcher Result

参照源码文档:
</br>file:///G:/csy/javaWeb/struct2%E6%A1%86%E6%9E%B6%E6%BA%90%E7%A0%81/Full%20Distribution/struts-2.5.14.1-all/struts-2.5.14.1/docs/docs/core-developers/dispatcher-result.html
</br>
</br>Includes or forwards to a view (usually a jsp). 
</br>Behind the scenes Struts will use a RequestDispatcher, 
<font color="#ff0000">
</br>where the target servlet/JSP receives the same request/response 
</br>objects as the original servlet/JSP. Therefore, you can pass data 
</br>between them using request.setAttribute() - the Struts action is available.
</font>
</br></br>
<font color="#ff0000">
此跳转方式为默认跳转方式 因为共用response,session等,所以可以利用session等传参,详细参照 <b>第五章:访问web元素</b>
</font>
<s:debug/>
</body>
</html>