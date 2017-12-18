package com.csy.struct2;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RequestSessionApplicationUsage01 extends ActionSupport {

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;

	public String putAttrs(){
		request = (Map<String, Object>) ActionContext.getContext().get("request");
		session = ActionContext.getContext().getSession();
		application = ActionContext.getContext().getApplication();
		
		request.put("name", "i am request");
		session.put("name", "i am session");
		application.put("name", "i am application");

		return SUCCESS;
	}
}
