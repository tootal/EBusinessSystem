package com.demo.dao;

import java.util.List;

import com.demo.model.Order;

/**
 * dao层接口-对数据库：订单表及订单商品表的操作
 * @author MouseHappy
 */
public interface OrderDao {

	/**
	 * 创建一个新订单
	 * @param order
	 */
	void add(Order order);

	/**
	 * 获取一个订单
	 * @param id
	 * @return Order
	 */
	Order find(String id);

	/**
	 * 获取指定状态下的订单列表
	 * @param state
	 * @return List<Order>
	 */
	List<Order> getAll(boolean state);

	/**
	 * 更新一个订单
	 * @param order
	 */
	void update(Order order);

	/**
	 * 获取指定用户下的指定状态的订单列表
	 * @param state
	 * @param userId
	 * @return List<Order>
	 */
	List<Order> getAll(boolean state, String userId);

	/**
	 * 获取指定用户下的订单列表
	 * @param userId
	 * @return List<Order>
	 */
	List<Order> getAllOrder(String userId);

}