package com.demo.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.demo.utils.JdbcUtils;
import com.demo.dao.AdminDao;
import com.demo.model.Admin;

/**
 * dao层实现-对数据库：管理表的操作
 * @author MouseHappy
 */
public class AdminDaoImpl implements AdminDao {
	/** (non-Javadoc)
	 * @see com.demo.dao.AdminDao#find(java.lang.Integer)
	 */
	@Override
	public Admin find(int id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from admin where id=?";// SQL语句
			return (Admin)runner.query(sql, id, new BeanHandler(Admin.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.AdminDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin find(String username, String password){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());// JDBC的调用者，数据库操作类，DBUtils工具，参数为c3p0连接池
			String sql = "select * from admin where username=? and password=?";// SQL语句
			Object params[] = {username, password};// 多个参数封装为对象数组
			return (Admin)runner.query(sql, params, new BeanHandler(Admin.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
