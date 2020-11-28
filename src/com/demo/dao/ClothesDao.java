package com.demo.dao;

import java.util.List;

import com.demo.model.Clothes;

import com.demo.model.Clothes;

/**
 * dao层接口-对数据库：商品表的操作
 * @author MouseHappy
 */
public interface ClothesDao {

	/**
	 * 创建一个新商品
	 * @param clothes
	 */
	void add(Clothes clothes);
	
	/**
	 * 修改一个新商品
	 * @param clothes
	 */
	void modify(Clothes clothes);
	
	/**
	 * 删除一个新商品
	 * @param clothes
	 */
	void delete(String id);

	/**
	 * 获取一个商品
	 * @param id
	 * @return Clothes
	 */
	Clothes find(String id);
	
	/**
	 * 获取商品列表分页数据
	 * @param startIndex
	 * @param pageSize
	 * @return List<Clothes>
	 */
	public List<Clothes> getPageData(int startIndex, int pageSize);
	
	/**
	 * 获取商品列表总数
	 * @return int
	 */
	public int getTotalRecord();

	/**
	 * 获取指定分类下的商品列表分页数据
	 * @param startIndex
	 * @param pageSize
	 * @param categoryId
	 * @return List<Clothes>
	 */
	public List<Clothes> getPageData(int startIndex, int pageSize, String categoryId);
	
	/**
	 * 获取指定分类下的商品列表总数
	 * @param categoryId
	 * @return int
	 */
	public int getTotalRecord(String categoryId);

}