package com.csy.struct2;

import java.util.Map;

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
public class RequestSessionApplicationUsage02 extends ActionSupport implements RequestAware,SessionAware,ApplicationAware{

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;

	public String putAttrs(){
		//直接使用,不需要初始化
		request.put("name", "i am request");
		session.put("name", "i am session");
		application.put("name", "i am application");
		return SUCCESS;
	}
}
