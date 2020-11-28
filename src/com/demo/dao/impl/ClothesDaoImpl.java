package com.demo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.demo.utils.JdbcUtils;
import com.demo.dao.ClothesDao;
import com.demo.model.Clothes;

/**
 * dao层实现-对数据库：商品表的操作
 * @author MouseHappy
 */
public class ClothesDaoImpl implements ClothesDao {
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#add(com.demo.model.Clothes)
	 */
	@Override
	public void add(Clothes clothes){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into clothes(id,name,brand,price,image,description,category_id) values(?,?,?,?,?,?,?)";// SQL语句
			Object params[] = {clothes.getId(), clothes.getName()
					, clothes.getBrand(), clothes.getPrice()
					, clothes.getImage(), clothes.getDescription()
					, clothes.getCategoryId()};// 多个参数封装为对象数组
			runner.update(sql, params);// 执行SQL语句
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#modify(com.demo.model.Clothes)
	 */
	@Override
	public void modify(Clothes clothes){
		try {
			//System.out.println(clothes.getName());
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update clothes set name=?,brand=?,price=?,image=?,description=? where id=?";// SQL语句
			Object params[] = { clothes.getName()
					, clothes.getBrand(), clothes.getPrice()
					, clothes.getImage(), clothes.getDescription()
					, clothes.getId()};// 多个参数封装为对象数组
			runner.update(sql, params);// 执行SQL语句
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#delete(com.demo.model.Clothes)
	 */
	@Override
	public void delete(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "delete from clothes where id=?";// SQL语句
			Object params[] = { id };// 多个参数封装为对象数组
			runner.update(sql, params);// 执行SQL语句
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#find(java.lang.String)
	 */
	@Override
	public Clothes find(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from clothes where id=?";// SQL语句
			return (Clothes)runner.query(sql, id, new BeanHandler(Clothes.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#getPageData(int, int)
	 */
	@Override
	public List<Clothes> getPageData(int startIndex, int pageSize){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from clothes limit ?,?";// SQL语句
			Object params[] = {startIndex, pageSize};// 多个参数封装为对象数组
			return (List<Clothes>)runner.query(sql, params, new BeanListHandler(Clothes.class));// 执行SQL语句，并使用BeanListHandler工具将结果转为List<POJO>对象
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#getTotalRecord()
	 */
	@Override
	public int getTotalRecord(){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from clothes";// SQL语句
			long totalrecord = (Long)runner.query(sql, new ScalarHandler());// 执行SQL语句，并使用ScalarHandler工具将结果转为Long类型
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#getPageData(int, int, java.lang.String)
	 */
	@Override
	public List<Clothes> getPageData(int startIndex, int pageSize, String categoryId){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from clothes where category_id=? limit ?,?";// SQL语句
			Object params[] = {categoryId, startIndex, pageSize};// 多个参数封装为对象数组
			return (List<Clothes>)runner.query(sql, params, new BeanListHandler(Clothes.class));// 执行SQL语句，并使用BeanListHandler工具将结果转为List<POJO>对象
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.ClothesDao#getTotalRecord(int)
	 */
	@Override
	public int getTotalRecord(String categoryId){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from clothes where category_id=?";// SQL语句
			long totalrecord = (Long)runner.query(sql, categoryId, new ScalarHandler());// 执行SQL语句，并使用ScalarHandler工具将结果转为Long类型
			return (int)totalrecord;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
