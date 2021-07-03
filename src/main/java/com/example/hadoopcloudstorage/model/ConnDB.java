package com.example.hadoopcloudstorage.model;


import java.sql.Connection;
import java.sql.DriverManager;


public class ConnDB {
	private Connection ct = null;

	public Connection getConn() {
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//得到连接
			ct = DriverManager.getConnection("jdbc:mysql://192.168.223.128:3306/Hadoop?user=window-user&password=@Eugene1244&useSSL=false");
		} catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
}
