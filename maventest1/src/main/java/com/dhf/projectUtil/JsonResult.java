package com.dhf.projectUtil;

import java.io.Serializable;

public class JsonResult implements Serializable{

	private Boolean success = false;// 返回是否成功
	private String msg = "";// 返回信息
	private Object obj = null;// 返回其他对象信息
	private String code = "";//提示代码

	public Boolean getSuccess() {
		return success;
	}

	public JsonResult setSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public JsonResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getObj() {
		return obj;
	}

	public JsonResult setObj(Object obj) {
		this.obj = obj;
		return this;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCode() {
		return code;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public JsonResult setCode(String code) {
		this.code = code;
		return this;
	}
	
	
}
