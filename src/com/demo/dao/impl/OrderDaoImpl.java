package com.demo.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.demo.utils.JdbcUtils;
import com.demo.dao.OrderDao;
import com.demo.model.Clothes;
import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.model.User;

/**
 * dao层实现-对数据库：订单表及订单商品表的操作
 * @author MouseHappy
 */
public class OrderDaoImpl implements OrderDao {
	
	/** (non-Javadoc)
	 * @see com.demo.dao.OrderDao#add(com.demo.model.Order)
	 */
	@Override
	public void add(Order order){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//1. 把order的基本信息保存到order表
			String sql = "insert into orders(id,ordertime,price,state,user_id) values(?,?,?,?,?)";// SQL语句
			Object params[] = {order.getId(), order.getOrderTime()
					, order.getPrice(), order.isState()
					, order.getUser().getId()};// 多个参数封装为对象数组
			runner.update(sql, params);// 执行SQL语句
			//2. 把order中的订单项保存到orderitem表中
			Set<OrderItem> set = order.getOrderItems();// 从订单中取出订单单项集
			for(OrderItem item : set){// 从订单单项集中取出订单单项
				sql = "insert into orderitem(id,quantity,price,order_id,clothes_id) values(?,?,?,?,?)";// SQL语句
				params = new Object[]{item.getId(), item.getQuantity()
						, item.getPrice(), order.getId()
						, item.getClothes().getId()};// 多个参数封装为对象数组
				runner.update(sql, params);// 执行SQL语句
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.OrderDao#find(java.lang.String)
	 */
	@Override
	public Order find(String id){
		
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//1.找出订单的基本信息
			String sql = "select * from orders where id=?";// SQL语句
			Order order = (Order) runner.query(sql, id, new BeanHandler(Order.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
			//2.找出订单中所有的订单单项
			sql = "select * from orderitem where order_id=?";// SQL语句
			List<OrderItem> list = (List<OrderItem>) runner.query(sql, id, new BeanListHandler(OrderItem.class));// 执行SQL语句，并使用BeanListHandler工具将结果转为List<POJO>对象
			for(OrderItem item : list){// 填充订单单项中的商品信息 
				sql = "select clothes.* from orderitem,clothes where orderitem.id=? and orderitem.clothes_id=clothes.id";// SQL语句
				Clothes clothes = (Clothes) runner.query(sql, item.getId(), new BeanHandler(Clothes.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
				item.setClothes(clothes);// 填充
			}
			//把找出的订单项放进order
			order.getOrderItems().addAll(list);
			//3.找出订单属于哪个用户
			sql = "select * from orders,user where orders.id=? and orders.user_id=user.id";// SQL语句
			User user = (User) runner.query(sql, order.getId(), new BeanHandler(User.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
			order.setUser(user);// 填充
			return order;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.OrderDao#getAll(boolean)
	 */
	@Override
	public List<Order> getAll(boolean state){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=?";// SQL语句
			List<Order> list = (List<Order>) runner.query(sql, state, new BeanListHandler(Order.class));// 执行SQL语句，并使用BeanListHandler工具将结果转为List<POJO>对象
			for(Order order : list){				
				//找出当前订单属于哪个用户
				sql = "select user.* from orders,user where orders.id=? and orders.user_id=user.id";// SQL语句
				User user = (User) runner.query(sql, order.getId(), new BeanHandler(User.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
				order.setUser(user);
			} 
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.OrderDao#getAll(boolean, java.lang.String)
	 */
	@Override
	public List<Order> getAll(boolean state, String userId){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=? and orders.user_id=?";// SQL语句
			Object params[] = {state, userId};// 多个参数封装为对象数组
			List<Order> list = (List<Order>) runner.query(sql, params, new BeanListHandler(Order.class));// 执行SQL语句，并使用BeanListHandler工具将结果转为List<POJO>对象
			//将所有该user加到list中
			for(Order order : list){// 填写订单的用户信息
				sql = "select * from user where user.id=?";// SQL语句
				User user = (User) runner.query(sql, userId, new BeanHandler(User.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
				order.setUser(user);
			}
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.OrderDao#getAllOrder(java.lang.String)
	 */
	@Override
	public List<Order> getAllOrder(String userId){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where user_id=?";// SQL语句
			List<Order> list = (List<Order>) runner.query(sql, userId, new BeanListHandler(Order.class));// 执行SQL语句，并使用BeanListHandler工具将结果转为List<POJO>对象
			//将所有该user加到List中去
			for(Order order : list){// 填写订单的用户信息
				sql = "select * from user where id=?";// SQL语句
				User user = (User) runner.query(sql, userId, new BeanHandler(User.class));// 执行SQL语句，并使用BeanHandler工具将结果转为POJO对象
				order.setUser(user);
			}
			
			return list;
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** (non-Javadoc)
	 * @see com.demo.dao.OrderDao#update(com.demo.model.Order)
	 */
	@Override
	public void update(Order order){//这里只改变发货状态，实际中还可以改变购买数量等其他信息，可以再完善
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update orders set state=? where id=?";// SQL语句
			Object params[] = {order.isState(), order.getId()};// 多个参数封装为对象数组
			runner.update(sql, params);// 执行SQL语句
		} catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
