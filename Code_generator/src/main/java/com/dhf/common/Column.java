package com.dhf.common;

/**
 * 表中的字段
 * Column.java
 * @author denghf
 * 2017年7月31日 下午8:04:16
 */
public class Column {
	
	private String column="";     //java字段名称
	
	private String getcolumn="";  //第一个字母大写
	
	private String mysqltype="";  //mysql 类型
	
	private String javatype="";  //JAVA  类型
	
	private String key="";   //主外建
	
	private String comment = ""; //注释

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getColumn() {
		return column;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMysqltype() {
		return mapperType(mysqltype);
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMysqltype(String mysqltype) {
		this.mysqltype = mysqltype;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public void setJavatype(String javatype) {
		this.javatype=javatype;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getJavatype() {
		return replaceType(mysqltype);
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getKey() {
		return key;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	
	public String replaceType(String type){
		if(type.contains("bigint")){
			return "Long";
		}
		if(type.contains("datetime")){
			return "Date";
		}
		if(type.contains("varchar")){
			return "String";
		}
		if(type.contains("int")){
			return "Integer";
		}
		if(type.contains("decimal")){
			return "BigDecimal";
		}
		
		return "String";
	}

	/**
	 * 首字母大写
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getGetcolumn() {
		getcolumn = column.substring(0, 1).toUpperCase() + column.substring(1);
	    return  getcolumn;
	}

	/**
	 * <p>如果注释为空都返回字段名称</p>
	 * @return:     String
	 */
	public String getComment() {
		if(comment!=null&&!"".equals(comment)){
			return comment;
		}
		return column;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * mapper里的jdbcType
	 * @return
	 */
	public String mapperType(String type){
		if(type.contains("bigint")){
			return "BIGINT";
		}
		if(type.contains("datetime")){
			return "TIMESTAMP";
		}
		if(type.contains("varchar")){
			return "VARCHAR";
		}
		if(type.contains("int")){
			return "INTEGER";
		}
		if(type.contains("decimal")){
			return "DECIMAL";
		}
		if(type.contains("tinyint")){
			return "TINYINT";
		}
		return "VARCHAR";
	}
}
