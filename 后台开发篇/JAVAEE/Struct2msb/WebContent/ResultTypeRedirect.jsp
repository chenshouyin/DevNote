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
Redirect Result
<br><br>
Calls the {@link HttpServletResponse#sendRedirect(String) sendRedirect} 
<br>method to the location specified. The response is told to redirect 
<br><font color="#ff0000">the browser to the specified location (a new request from the client).</font> 
<br><font color="#ff0000">The consequence of doing this means that the action (action instance, action errors, field errors, etc) that was just executed is lost and no longer available.</font>  This is <font color="#ff0000">because actions are built on a single-thread model</font>. The only way to pass data is through the session or with web parameters (url?name=value) which can be OGNL expressions.
<s:debug/>
</body>
</html>