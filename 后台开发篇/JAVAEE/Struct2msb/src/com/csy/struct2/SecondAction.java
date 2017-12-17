package com.csy.struct2;

import com.opensymphony.xwork2.ActionSupport;

public class SecondAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	} 
	public String hello() throws Exception{
		System.out.println("跳转");
		return SUCCESS;
	}
	
	
	public String add() throws Exception{
		System.out.println("跳转");
		return ERROR;
	}
}
