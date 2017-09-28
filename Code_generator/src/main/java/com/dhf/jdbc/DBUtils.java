package com.dhf.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dhf.common.Column;
import com.dhf.common.Table;







/**
 * DBUtils.java
 * @author denghf
 * 2017年7月31日 下午8:02:52
 */
public class DBUtils {
	
	
	public static Table getTabel(String tableName){
    	DBHelper db = new DBHelper("show full columns from "+tableName+" ");
    	Table table  = new Table();
    	ArrayList<Column> list = new ArrayList<Column>();
    	try {
			ResultSet ret = db.pst.executeQuery();
			while (ret.next()) {
				Column column = new Column();
				column.setColumn(ret.getString(1));
				column.setMysqltype(ret.getString(2));
				column.setKey(ret.getString(5));
				column.setComment(ret.getString(9));
				list.add(column);
            }//显示数据  
			//存入字段,字段排序，主键放在第一位
			table.setColumns(list);
            ret.close();  
            db.close();//关闭连接
            return table;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return null;
	}
	
}
