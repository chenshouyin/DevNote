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

<a href="<%=basePath %>test/RequestSessionApplicationUsage01_putAttrs">5.访问web元素:取Map类型的request、session、application01</a>
</br>
<a href="<%=basePath %>test/RequestSessionApplicationUsage02_putAttrs">5.访问web元素:取Map类型的request、session、application02<font color="#ff0000">(最常用的一种,实现RequestAware等接口)</font></a>
</br>
<a href="<%=basePath %>test/RequestSessionApplicationUsage03_putAttrs">5.访问web元素:真实类型的Httprequest、session、application03</a>
</br>
<a href="<%=basePath %>test/RequestSessionApplicationUsage04_putAttrs">5.访问web元素:真实类型的Httprequest、session、application04</a>
</br>
</br>
<a href="<%=basePath %>test/TestIncludeAction06_test">6.使用include标签(注意不要加后缀:<include file="struts_6"></include>)</a>
</br>
</br>
<a href="<%=basePath %>test/not_exist_action">7.默认Action,如果敲一个不存在的Action的话,默认跳到默认页面<font color="#ff0000">(1.貌似不能放在include标签里面 2.如果加了通配符,类似 00_kk 则要按照此格式才不会报错)</font></a>
</br>
</br>

<a href="<%=basePath %>test/resultType">8.Result类型01:ResultTypeDispatcher</a>		
</br>
<a href="<%=basePath %>test/resultTypeRedirect">8.Result类型02:ResultTypeRedirect</a>
（地址栏的地址是:http://localhost:8080/Struct2msb/WEB-INF/jsp/ResultTypeRedirect.jsp   404 客户端跳转方式不能直接访问web-inf下的文件
<a href="http://www.jianshu.com/p/123bfa241c73">详见:访问WebContent/WEB-INF下的jsp页面404</a>）
<br>其它type见文档file:///G:/csy/javaWeb/struct2%E6%A1%86%E6%9E%B6%E6%BA%90%E7%A0%81/Full%20Distribution/struts-2.5.14.1-all/struts-2.5.14.1/docs/docs/core-developers/result-types.html
<br>
<br>
<a href="<%=basePath %>test/testGlobal?type=1">9.全局结果集01:Gloable_result 同一包名下可直接用,不同包名可继承上述包再使用 </a>
<br><a href="<%=basePath %>test/testGlobal?type=0">9.全局结果集02:Gloable_result 同一包名下可直接用,不同包名可继承上述包再使用 </a>

</body>
</html>