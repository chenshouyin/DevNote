package com.csy.struct2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 最常用的一种,strut会自动映射赋值
 * @author chenshouyin
 *
 */
public class RequestSessionApplicationUsage03 extends ActionSupport {

	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	public RequestSessionApplicationUsage03() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		application = session.getServletContext();
	}
	
	public String putAttrs() {
		//构造方法里面初始化 直接使用,不需要初始化
		request.setAttribute("name", "i am request");
		session.setAttribute("session", "i am request");
		application.setAttribute("application", "i am request");
		return SUCCESS; 
	}
}
