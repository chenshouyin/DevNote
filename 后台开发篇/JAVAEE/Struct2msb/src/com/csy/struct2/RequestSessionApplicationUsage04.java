package com.csy.struct2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 最常用的一种,strut会自动映射赋值
 * @author chenshouyin
 *
 */
public class RequestSessionApplicationUsage04 extends ActionSupport  implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	

	
	public String putAttrs() {
		//构造方法里面初始化 
		request.setAttribute("name", "i am request");
		session.setAttribute("session", "i am request");
		application.setAttribute("application", "i am request");
		return SUCCESS; 
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}
	
	
	
	
	
	
}
