<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>Struts-Tags学习</title>
</head>
<body>
	<ol>
		<li>property: <s:property value="username"/> </li>
		<li>property 取值为字符串: <s:property value="'username'"/> </li>
		<li>property 设定默认值: <s:property value="admin" default="管理员"/> </li>
		<li>property 设定HTML: <s:property value="'<hr/>'" escape="false"/> </li>
		<hr />
		<li>set 设定adminName值（默认为request 和 ActionContext）: <s:set var="adminName" value="username" /></li>
		
		<li>set 从request取值: <s:property value="#request.adminName" /></li>
		<li>set 从ActionContext取值: <s:property value="#adminName" /></li>
		
		<%--<li>set 设定范围: <s:set name="adminPassword" value="password" scope="page"/></li>
		<li>set 从相应范围取值: <%=pageContext.getAttribute("adminPassword") %></li>
		--%>
		<li>set 设定var，范围为ActionContext: <s:set var="adminPassword" value="password" scope="session"/></li>
		<li>set 使用#取值: <s:property value="#adminPassword"/> </li>
		<li>set 从相应范围取值: <s:property value="#session.adminPassword"/> </li>
		
		<hr />
		
		<%--<li>push:<s:set name="myDog" value="new com.bjsxt.struts2.ognl.Dog('oudy')"></s:set></li>
		<li>
		push:<s:push value="#myDog">
			<s:property value="name"/>
		</s:push>
		</li>
		<li>push: <s:property value="name"/></li>
		--%>
		
		<hr />
		<li>bean 定义bean,并使用param来设定新的属性值:
			<s:bean name="com.bjsxt.struts2.tags.Dog" >
				<s:param name="name" value="'pp'"></s:param>
				<s:property value="name"/>
				
			</s:bean>
			
			
		</li>
		
		<li>bean 查看debug情况:
			<s:bean name="com.bjsxt.struts2.tags.Dog" var="myDog">
				<s:param name="name" value="'oudy'"></s:param>
			</s:bean>
			拿出值：
			<s:property value="#myDog.name"/>
			
		</li>
		<hr />
		
		<li>include _include1.html 包含静态英文文件
		<s:include value="/_include1.html"></s:include>
		</li>
		
		<li>include _include2.html 包含静态中文文件
		<s:include value="/_include2.html"></s:include>
		</li>
		
		<li>include _include1.html 包含静态英文文件，说明%用法
		<s:set var="incPage" value="%{'/_include1.html'}" />
		<s:include value="%{#incPage}"></s:include>
		</li>
		
		
		<hr />
		
		<li>if elseif else: 
		age = <s:property value="#parameters.age[0]" /> <br />
		<s:set var="age" value="#parameters.age[0]" />
		<s:if test="#age < 0">wrong age!</s:if>
		<s:elseif test="#parameters.age[0] < 20">too young!</s:elseif>
		<s:else>yeah!</s:else><br />
		
		<s:if test="#parameters.aaa == null">null</s:if>
		</li>
		
		<hr />
		
		<li>遍历集合：<br />
		<s:iterator value="{1, 2, 3}" >
			<s:property/> |
		</s:iterator>
		</li>
		<li>自定义变量：<br />
		<s:iterator value="{'aaa', 'bbb', 'ccc'}" var="x">
			<s:property value="#x.toUpperCase()"/> |
		</s:iterator>
		</li>
		<li>使用status:<br />
		<s:iterator value="{'aaa', 'bbb', 'ccc'}" status="status">
			<s:property/> | 
			遍历过的元素总数：<s:property value="#status.count"/> |
			遍历过的元素索引：<s:property value="#status.index"/> |
			当前是偶数？：<s:property value="#status.even"/> |
			当前是奇数？：<s:property value="#status.odd"/> |
			是第一个元素吗？：<s:property value="#status.first"/> |
			是最后一个元素吗？：<s:property value="#status.last"/>
			<br />
		</s:iterator>
		
		</li>
		
		<li>
		<s:iterator value="#{1:'a', 2:'b', 3:'c'}" >
			<s:property value="key"/> | <s:property value="value"/> <br />
		</s:iterator>
		</li>
		
		<li>
		<s:iterator value="#{1:'a', 2:'b', 3:'c'}" var="x">
			<s:property value="#x.key"/> | <s:property value="#x.value"/> <br />
		</s:iterator>
		</li>
		
		<li>
		
		<s:fielderror fieldName="fielderror.test" theme="simple"></s:fielderror>
		
		</li>
	</ol>
	
	
	
	
</body>
</html>