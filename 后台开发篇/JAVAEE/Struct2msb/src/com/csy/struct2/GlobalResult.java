package com.csy.struct2;

import com.opensymphony.xwork2.ActionSupport;

public class GlobalResult extends ActionSupport {

	//一定要有get和set方法菜鸟赋值成功
	private String type;

	public String doAction() {
		if ("1".equals(type)) {
			return SUCCESS;
		}
		// 全局结果集的name1,同一包下可直接使用,不同包可继承使用
		return "global_default";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
