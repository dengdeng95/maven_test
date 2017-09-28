package com.dhf.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 表
 * Table.java
 * @author denghf
 * 2017年7月31日 下午8:03:42
 */
public class Table {
	
	private List<Column>  columns;

	/**
	 * <p> TODO</p>
	 * @return:     List<Column>
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/** 
	 * <p> TODO</p>
	 * @return: List<Column>
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	
	public Set<String> getColumnsType(){
		Set<String> set = new HashSet<String>();
		for(Column c : columns){
			set.add(c.getJavatype());
			c.setJavatype(c.getJavatype());
		}
		return set;
	}
	
}
