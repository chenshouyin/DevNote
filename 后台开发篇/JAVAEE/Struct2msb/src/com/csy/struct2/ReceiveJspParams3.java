package com.csy.struct2;

import com.opensymphony.xwork2.ActionSupport;

public class ReceiveJspParams3 extends ActionSupport{
	//一定要有get和set方法
	//User一定要是public的不然struct无法找到
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Struct 会自动赋值
	 * @return
	 */
	public String receiveParams(){
		System.out.println("==接收到参数name:"+user.getName());
		return SUCCESS;
	}
	


}


