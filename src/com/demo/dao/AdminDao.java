package com.demo.dao;

import com.demo.model.Admin;

/**
 * dao层接口-对数据库：管理表的操作
 * @author MouseHappy
 */
public interface AdminDao {

	/**
	 * 获取一个管理
	 * @param id
	 * @return Admin
	 */
	Admin find(int id);

	/**
	 * 管理登录验证
	 * @param username
	 * @param password
	 * @return Admin
	 */
	Admin find(String username, String password);

}
