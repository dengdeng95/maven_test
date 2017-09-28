package com.dhf.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dhf.run.GeneratorCode;


/**
 * 连接数据库
 * DBHelper.java
 * @author denghf
 * 2017年7月31日 下午8:04:38
 */
public class DBHelper {  
    public static final String name = "com.mysql.jdbc.Driver";
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(GeneratorCode.config.getProperty("url"),GeneratorCode.config.getProperty("username"),GeneratorCode.config.getProperty("password"));//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
    
    public static void main(String[] args){
    	/*DBHelper db = new DBHelper("show columns from app_user");
    	try {
			ResultSet ret = db.pst.executeQuery();
			while (ret.next()) {  
                String column = ret.getString(1);  
                String type = ret.getString(2);  
                System.out.println(column +"========="+type );  
            }//显示数据  
            ret.close();  
            db.close();//关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
    	String path = DBHelper.class.getResource("/model.ftl").getPath().substring(1);
    	System.out.println(path);
    }
    
}  
