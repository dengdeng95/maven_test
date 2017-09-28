package com.dhf.quartz;

public class Job {

	private String beanClass;//类的路径
	
	private String name;//方法名
	
	private String num;//时间点
	

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
