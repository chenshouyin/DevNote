package com.csy.struct2;
import com.opensymphony.xwork2.ActionSupport;

public class HelloStruct2 extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("====HelloStruct2");
		return "success";
	}

}
