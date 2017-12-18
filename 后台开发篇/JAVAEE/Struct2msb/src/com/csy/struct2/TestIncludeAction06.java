package com.csy.struct2;

import com.opensymphony.xwork2.ActionSupport;

public class TestIncludeAction06 extends ActionSupport{

	public String test(){
		System.out.println("跳转jsp,配置文件用include包含");
		return SUCCESS;
	}
}
