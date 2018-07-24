package org.apache.hadoop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
	private static final String url = "jdbc:mysql://localhost:3306/test";
	private static final String user = "root";
	private static final String password = "123456";
	private static final String driver = "com.mysql.jdbc.Driver";
	static{
		try {
			Class.forName(driver);//0.执行且执行一次，加载数据库驱动程序
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		try{
			Connection connection=DriverManager.getConnection(url,user,password);
			return connection;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
