package com.demo.dao;

import com.demo.model.User;

/**
 * dao层接口-对数据库：用户表的操作
 * @author MouseHappy
 */
public interface UserDao {

	/**
	 * 注册一个新用户
	 * @param user
	 */
	void add(User user);

	/**
	 * 获取一个用户
	 * @param id
	 * @return User
	 */
	User find(String id);

	/**
	 * 用户登录验证
	 * @param username
	 * @param password
	 * @return User
	 */
	User find(String username, String password);

}