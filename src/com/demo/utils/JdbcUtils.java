package com.demo.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * utils-jdbc工具类
 * @author MouseHappy
 */
public class JdbcUtils {
	
	private static DataSource ds = null;
	static{
		ds = new ComboPooledDataSource();
	}
	
	/**
	 * 获取c3p0连接池
	 * @return
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 连接数据库
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}
