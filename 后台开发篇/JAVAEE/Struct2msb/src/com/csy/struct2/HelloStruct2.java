package com.csy.struct2;

import com.opensymphony.xwork2.ActionSupport;

public class HelloStruct2 extends ActionSupport {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("====HelloStruct2");
		return "execute";
	}

	
	public String index()  {
		// TODO Auto-generated method stub
		System.out.println("====HelloStruct2");
		return "SUCCESS";
	}
//	
	
	public String add()  {
		// TODO Auto-generated method stub
		System.out.println("====HelloStruct2");
		return "ADD";
	}
}
