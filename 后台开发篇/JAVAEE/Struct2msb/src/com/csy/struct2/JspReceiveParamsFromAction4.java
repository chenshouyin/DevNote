package com.csy.struct2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.opensymphony.xwork2.ActionSupport;

public class JspReceiveParamsFromAction4 extends ActionSupport {

	public String receiveParams() {
		Map<String, List<String>> errorMap = new HashMap<>();
		List<String> erros1 = new ArrayList<>();
		erros1.add("参数校验错误信息1");
		erros1.add("参数校验错误信息2");
		errorMap.put("erro1", erros1);

		List<String> erros2 = new ArrayList<>();
		erros2.add("错误信息21");
		erros2.add("错误信息22");
		errorMap.put("erro1", erros2);
		this.setFieldErrors(errorMap);
		return SUCCESS;
	}
}
