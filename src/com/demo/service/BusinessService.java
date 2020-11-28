package com.demo.service;

import java.util.List;

import com.demo.model.Cart;
import com.demo.model.Category;
import com.demo.model.Clothes;
import com.demo.model.Order;
import com.demo.model.Page;
import com.demo.model.User;
import com.demo.model.Admin;
import com.demo.model.Statistics;

/**
 * 控制层-对应于系统的主要功能
 * @author MouseHappy
 */
public interface BusinessService {

	/**
	 * 创建一个新分类
	 * @param category
	 */
	void addCategory(Category category);

	/**
	 * 获取一个分类
	 * @param id
	 * @return Category
	 */
	Category findCategory(String id);

	/**
	 * 获取全部分类
	 * @return List<Category>
	 */
	List<Category> getAllCategory();
	
	/**
	 * 创建一个新商品
	 * @param clothes
	 */
	void addClothes(Clothes clothes);
	
	/**
	 * 修改一个新商品
	 * @param clothes
	 */
	void modifyClothes(Clothes clothes);
	
	/**
	 * 删除一个新商品
	 * @param clothes
	 */
	void deleteClothes(String id);

	/**
	 * 获取一个商品
	 * @param id
	 * @return Clothes
	 */
	Clothes findClothes(String id);
	
	/**
	 * 获取商品列表分页数据
	 * @param startIndex
	 * @param pageSize
	 * @return List<Clothes>
	 */
	Page getClothesPageData(String pageNum);
	
	/**
	 * 获取指定分类下的商品列表分页数据
	 * @param startIndex
	 * @param pageSize
	 * @param categoryId
	 * @return List<Clothes>
	 */
	Page getClothesPageData(String pageNum, String categoryId);
	
	
	/**
	 * 将商品加入购物车
	 * @param cart
	 * @param clothes
	 */
	void buyClothes(Cart cart, Clothes clothes);
	
	/**
	 * 注册一个新用户
	 * @param user
	 */
	void registerUser(User user);
	
	/**
	 * 获取一个用户
	 * @param id
	 * @return User
	 */
	User findUser(String id);
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return User
	 */
	User userLogin(String userName, String password);
	
	/**
	 * 获取一个管理
	 * @param id
	 * @return Admin
	 */
	Admin findAdmin(int id);
	
	/**
	 * 管理登录
	 * @param username
	 * @param password
	 * @return Admin
	 */
	Admin adminLogin(String userName, String password);
	
	/**
	 * 生成订单
	 * @param cart
	 * @param user
	 */
	void createOrder(Cart cart, User user);
	
	/**
	 * 获取指定状态下的订单列表
	 * @param state
	 * @return List<Order>
	 */
	List<Order> listOrder(String state);
	
	/**
	 * 列出订单明细
	 * @param orderId
	 * @return Order
	 */
	Order findOrder(String orderId);
	
	/**
	 * 把订单置为发货状态
	 * @param orderId
	 */
	void confirmOrder(String orderId);
	
	/**
	 * 获取指定用户下的指定状态的订单列表
	 * @param state
	 * @param userId
	 * @return List<Order>
	 */
	List<Order> listOrder(String state, String userId);
	
	/**
	 * 获取指定用户下的订单列表
	 * @param userId
	 * @return List<Order>
	 */
	List<Order> clientListOrder(String userId);
	
	/**
	 * 获取统计报表
	 * @return List<Statistics>
	 */
	List<Statistics> showStatistics();
}