package com.demo.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.demo.utils.JdbcUtils;
import com.demo.dao.UserDao;
import com.demo.model.User;

/**
 * dao层实现-对数据库：用户表的操作
 * @author MouseHappy
 */
public class UserDaoImpl implements UserDao {

	/** (non-Javadoc)
	 * @see com.demo.dao.UserDao#add(com.demo.model.User)
	 */
	@Override
	public void add(User user){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(id,username,password,phone,cellphone,address,email) values(?,?,?,?,?,?,?)";// SQL语句
			Object params[] = {user.getId(), user.getUsername()
					, user.getPassword(), user.getPhone()
					, user.getCellphone(), user.getAddress()
					, user.getEmail()};// 多个参数封装为对象数组
			runner.update(sql, params);// 执行SQL语句
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.UserDao#find(java.lang.String)
	 */
	@Override
	public User find(String id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where id=?";// SQL语句
			return (User)runner.query(sql, id, new BeanHandler(User.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String username, String password){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";// SQL语句
			Object params[] = {username, password};// 多个参数封装为对象数组
			return (User)runner.query(sql, params, new BeanHandler(User.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
