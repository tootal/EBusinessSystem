package com.demo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.demo.utils.JdbcUtils;
import com.demo.dao.CategoryDao;
import com.demo.model.Category;

/**
 * dao层实现-对数据库：分类表的操作
 * @author MouseHappy
 */
public class CategoryDaoImpl implements CategoryDao {
	
	/** (non-Javadoc)
	 * @see com.demo.dao.CategoryDao#add(com.demo.model.Category)
	 */
	@Override
	public void add(Category category){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into category(id,name,description) values(?,?,?)";// SQL语句
			Object params[] = {category.getId(), category.getName()
					, category.getDescription()};// 多个参数封装为对象数组
			runner.update(sql, params);// 执行SQL语句
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.CategoryDao#find(java.lang.String)
	 */
	@Override
	public Category find(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category where id=?";// SQL语句
			return (Category)runner.query(sql, id, new BeanHandler(Category.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.CategoryDao#getAll()
	 */
	@Override
	public List<Category> getAll(){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category";// SQL语句
			return (List<Category>)runner.query(sql, new BeanListHandler(Category.class));// 执行SQL语句，并使用BeanListHandler工具将结果转为List<POJO>对象
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}

}
