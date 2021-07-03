package com.example.hadoopcloudstorage.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	private Statement sm = null;
	private Connection ct = null;
	private ResultSet rs = null;

	public void close() {
		try {
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (ct != null) {
				ct.close();
				ct = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			// TOD0 Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 检查登录用户是否合法
	public boolean checkUser(String user, String password) {
		boolean b = false;
		try {
			// 获得连接
			ct = new ConnDB().getConn();
			// 创建statement
			sm = ct.createStatement();
			String sql = "select * from student where name=\"" + user + "\"";
			rs = sm.executeQuery(sql);
			if (rs.next()) {
				// 说明用户存在
				String pwd = rs.getString(3);
				if (password.equals(pwd)) {
					// 说明密码正确
					b = true;
				} else {
					b = false;
				}
			} else {
				b = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}

	public void insert(String name, String password) throws SQLException {
		int i = 0;
		// 获得连接
		ct = new ConnDB().getConn();
		// 创建statement
		sm = ct.createStatement();
		String sql = "insert into student (name, password) values ('" + name + "','" + password + "')";
		System.out.println(sql + "333333333");
		i = sm.executeUpdate(sql);
	}
}
