package com.bjsxt.struts2.tags;

import com.opensymphony.xwork2.ActionSupport;

public class TagsAction extends ActionSupport {

	private String password;

	private String username;

	public TagsAction() {
	}

	public String execute() {
		this.addFieldError("fielderror.test", "wrong!");
		return SUCCESS;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
