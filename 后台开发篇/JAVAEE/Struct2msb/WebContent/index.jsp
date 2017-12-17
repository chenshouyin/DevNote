<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Struct2索引</title>
</head>
<body>
</br>
<font color="#ff0000">response.getWriter().println是java里面的,不会换行,要用jsp里面的out</font>
</br>
path:<% response.getWriter().println(path);%>
</br>
basePath:<% response.getWriter().println(basePath);%>
</br>
</br>
<font color="#ff0000">jsp里面的内置的out</font>
</br>
path:<%out.print(path); %>
</br>
basePath:<%out.print(basePath); %>
</br>
</br>
<a href="<%=basePath %>test/hello">1.配置Action和namsespace</a>
</br>
</br>
<a href="<%=basePath %>test/SecondAction_hello">2.通配符01</a>
</br>
<a href="<%=basePath %>test/SecondAction_add">2.通配符02</a>
</br>
</br>
<a href="<%=basePath %>test/ReceiveJspParams3_receiveParams?user.name=jack&password=heheheh">3.jsp向java class action传参</a>
</br>
</br>
<a href="<%=basePath %>test/JspReceiveParamsFromAction4_receiveParams">4.java class action向jsp传参</a>
</br>
</br>
</body>
</html>