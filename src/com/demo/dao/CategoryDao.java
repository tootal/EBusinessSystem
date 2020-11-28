package com.demo.dao;

import java.util.List;

import com.demo.model.Category;

/**
 * dao层接口-对数据库：分类表的操作
 * @author MouseHappy
 */
public interface CategoryDao {

	/**
	 * 创建一个新分类
	 * @param category
	 */
	void add(Category category);

	/**
	 * 获取一个分类
	 * @param id
	 * @return Category
	 */
	Category find(String id);

	/**
	 * 获取全部分类
	 * @return List<Category>
	 */
	List<Category> getAll();

}