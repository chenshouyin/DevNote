package com.csy.struct2;

import com.opensymphony.xwork2.ActionSupport;

/**
 * #用于写ognl表达式获取数据，
 * % 控制ognl表达式是否解析 ，
 * $ 用于配置文件获取值栈的数据 
 * @author Administrator
 *
 */
public class DynamicResultAction extends ActionSupport {

	//一定要有get和set方法菜鸟赋值成功
	private String type;
	private String result;
	public String doAction() {
		if ("1".equals(type)) {
			//此处设置的值可在strut配置文件里面获取$
			setResult("/WEB-INF/jsp/DynamicResultType1.jsp");
		}else {
			//此处设置的值可在strut配置文件里面获取$
			setResult("/WEB-INF/jsp/DynamicResultType2.jsp");
		}
		
        return SUCCESS;    
	}

	public String getResult() {
		return result;
	}

	public String getType() {
		return type;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setType(String type) {
		this.type = type;
	}
}
