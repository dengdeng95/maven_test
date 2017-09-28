package com.dhf.model;

public class Pager {
	private int startSize = 0;//当前页数
	private int allSize;//总条数
	private int allNum;//总页数
	private int pageSize;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartSize() {
		return startSize;
	}
	public void setStartSize(int startSize) {
		this.startSize = startSize;
	}
	public int getAllSize() {
		return allSize;
	}
	public void setAllSize(int allSize) {
		this.allSize = allSize;
	}
	public int getAllNum() {
		if(allSize%5==0){
			allNum = allSize / 5;
		}else{
			allNum = allSize / 5;
			allNum +=1;
		}
		return allNum;
	}
	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
}
